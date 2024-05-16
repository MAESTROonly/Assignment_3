public class BinarySearchTree<K extends Comparable<K>, V> {
    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            left = null;
            right = null;
        }
    }

    public void put(K key, V val) {
        root = addRecursive(root, key, val);
    }

    private Node addRecursive(Node current, K key, V val) {
        if (current == null) {
            return new Node(key, val);
        }

        if (key.compareTo(current.key) < 0) {
            current.left = addRecursive(current.left, key, val);
        } else if (key.compareTo(current.key) > 0) {
            current.right = addRecursive(current.right, key, val);
        } else {
            current.val = val;
        }

        return current;
    }

    public V get(K key) {
        return getRecursive(root, key);
    }

    private V getRecursive(Node current, K key) {
        if (current == null) {
            return null;
        }

        if (key.compareTo(current.key) == 0) {
            return current.val;
        }

        if (key.compareTo(current.key) < 0) {
            return getRecursive(current.left, key);
        } else {
            return getRecursive(current.right, key);
        }
    }

    public void delete(K key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node node, K key) {
        if (node == null) {
            return node;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = deleteNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node inOrderSuccessor = node.right;
            Node inOrderSuccessorParent = node;
            while (inOrderSuccessor.left!= null) {
                inOrderSuccessorParent = inOrderSuccessor;
                inOrderSuccessor = inOrderSuccessor.left;
            }

            node.key = inOrderSuccessor.key;
            node.val = inOrderSuccessor.val;

            if (inOrderSuccessor == node.right) {
                node.right = inOrderSuccessor.right;
            } else {
                inOrderSuccessorParent.left = inOrderSuccessor.right;
            }
        }

        return node;
    }

    public Iterable<K> iterator() {
        return new BSTIterator();
    }

    private abstract class BSTIterator implements Iterable<K> {
        private Node current;

        public BSTIterator() {
            current = root;
            while (current.left!= null) {
                current = current.left;
            }
        }

        public boolean hasNext() {
            return current!= null;
        }

        public K next() {
            K key = current.key;
            if (current.right!= null) {
                current = current.right;
                while (current.left!= null) {
                    current = current.left;
                }
            } else {
                Node parent = root;
                Node child = current;
                while (parent.right == child) {
                    child = parent;
                    parent = parent.left;
                }
                current = parent;
            }
            return key;
        }
    }
}
