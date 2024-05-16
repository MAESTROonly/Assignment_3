public class Main {
    public static void main(String[] args) {
        MyHashTable<String, Integer> hashTable = new MyHashTable<>();
        hashTable.put("Rauan", 10);
        hashTable.put("cat", 20);
        hashTable.put("orange", 30);
        hashTable.put("pineapple", 40);
        hashTable.put("apple", 50);
        System.out.println(hashTable.get("cat"));
        System.out.println(hashTable.get("banana"));
        System.out.println( hashTable.contains("apple"));
        hashTable.remove("orange");
        System.out.println(hashTable.getKey(10));

    }
}
