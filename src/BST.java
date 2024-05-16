public class BST {
    public static void main(String[] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
        bst.put(1, "one");
        bst.put(2, "two");
        bst.put(3, "three");
        bst.put(4, "four");
        bst.put(5, "five");

        System.out.println("In-order traversal using iterator:");
        for (Integer key : bst) {
            System.out.print(bst.get(key) + " ");
        }
    }
}
