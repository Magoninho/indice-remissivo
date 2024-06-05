public class BinarySearchTreeString {
    Node<String> root;

    private boolean isStringGreaterThan(String string1, String string2) {
        return (string1.compareTo(string2) > 0);
    }

    public void insert(Node<String> node) {
        root = insertHelper(root, node);
    }

    private Node<String> insertHelper(Node<String> root, Node<String> node) {
        String data = node.data;

        // if it's the first element
        if (root == null) {
            root = node;
            return root;
        } else if (!isStringGreaterThan(data, root.data)) {
            root.left = insertHelper(root.left, node);
        } else {
            root.right = insertHelper(root.right, node);
        }

        return root;
    }

    public void display() {
        displayHelper(root);
    }

    private void displayHelper(Node<String> root) {
        if (root != null) {
            displayHelper(root.left);
            System.out.println(root.data);
            displayHelper(root.right);
        }
    }

    public boolean search(String data) {
        return searchHelper(root, data);
    }

    private boolean searchHelper(Node<String> root, String data) {
        if (root == null) {
            return false;
        } else if (root.data.equals(data)) {
            return true;
        } else if (isStringGreaterThan(root.data, data)) {
            return searchHelper(root.left, data);
        } else {
            return searchHelper(root.right, data);
        }
    }

    public void remove(String data) {
        if (search(data)) {
            removeHelper(root, data);
        } else {
            System.out.println("não foi encontrado");
        }
    }

    private Node<String> removeHelper(Node<String> root, String data) {
        if (root == null) {
            return root;
        } else if (isStringGreaterThan(root.data, data)) {
            root.left = removeHelper(root.left, data);
        } else if (isStringGreaterThan(data, root.data)) {
            root.right = removeHelper(root.right, data);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.data = successor(root);
                root.right = removeHelper(root.right, root.data);
            } else {
                root.data = predecessor(root);
                root.left = removeHelper(root.left, root.data);
            }
        }
        return root;
    }

    private String successor(Node<String> root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    private String predecessor(Node<String> root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }
}
