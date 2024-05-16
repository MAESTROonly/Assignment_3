
public class MyHashTable<K,V> {
    private static class HashNode<K,V> {
        private final K key;
        private V value;
        private HashNode<K,V> next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return "{" + key + ", " + value + "}";
        }
    }
    private final HashNode<K,V> [] chainArray;
    private int M=11;
    private int size;
    public MyHashTable() {
        // Initialize chainArray with size M
        chainArray = (HashNode<K, V>[]) new HashNode[M];
        // Initialize each element of chainArray to null
        for (int i = 0; i < M; i++) {
            chainArray[i] = null;
        }
    }
    public MyHashTable(int M) {
        this.M = M;
        chainArray = (HashNode<K, V>[]) new HashNode[M];
        for (int i = 0; i < M; i++) {
            chainArray[i] = null;
        }
    }


    private int hash(K key) {
        int index = key.hashCode();
        return Math.abs(index % M);
    }
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K,V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        HashNode<K,V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }
    public V get(K key) {
        int index = hash(key);
        HashNode<K,V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        HashNode<K,V> node = chainArray[index];
        HashNode<K,V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return;
            }
            prev = node;
            node = node.next;
        }
    }
    public boolean contains(K key) {
        int index = hash(key);
        HashNode<K,V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }
    public K getKey(V value) {
        for (HashNode<K,V> node : chainArray) {
            while (node != null) {
                if (node.value == value) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }
}
