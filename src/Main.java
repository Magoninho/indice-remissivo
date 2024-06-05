public class Main {
    public static void main(String[] args) {
        BinarySearchTreeString tree = new BinarySearchTreeString();

        tree.insert(new Node<>("programming"));
        tree.insert(new Node<>("programs"));
        tree.insert(new Node<>("easy"));
        tree.insert(new Node<>("by"));
        tree.insert(new Node<>("human-engineered"));
        tree.insert(new Node<>("and"));
        tree.insert(new Node<>("be"));
        tree.insert(new Node<>("to"));

        tree.display();

        tree.remove("easy");
        tree.remove("programming");
        System.out.println("after removing");
        tree.display();
    }
}