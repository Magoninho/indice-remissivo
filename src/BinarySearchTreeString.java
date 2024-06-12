import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeString {
    Node<String> root;
    public int length;

    /**
     * Esse método serve para comparar se uma string é maior que a outra em ordem alfabética
     * Ex: A > B: False
     *     B > A: True (pois está mais a frente no alfabeto)
     * @param string1 Primeira string a ser comparada
     * @param string2 Segunda string a ser comparada
     * @return Se a primeira string é maior que a segunda
     */
    private boolean isStringGreaterThan(String string1, String string2) {
        return (string1.toLowerCase().compareTo(string2.toLowerCase()) > 0);
    }

    /**
     * Esse método insere um valor na árvore
     * @param node Nó do tipo String para ser inserido
     */
    public void insert(Node<String> node) {
        root = insertHelper(root, node);
        length++;
    }

    private Node<String> insertHelper(Node<String> root, Node<String> node) {
        String data = node.data;

        // if it's the first element
        if (root == null) {
            root = node;
            return root;
        } else if (isStringGreaterThan(root.data, data)) {
            root.left = insertHelper(root.left, node);
        } else if (isStringGreaterThan(data, root.data)) {
            root.right = insertHelper(root.right, node);
        }

        return root;
    }

    /**
     * Esse método imprime a lista Em Ordem
     */
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

    /**
     * Esse método retorna uma lista com os valores da árvore ordenados alfabeticamente
     * @return Uma lista com os valores da árvore ordenados alfabeticamente
     */
    public List<String> getNodesAlphabetically() {
        List<String> nodes = new ArrayList<String>();
        getNodesHelper(root, nodes);
        return nodes;
    }

    private void getNodesHelper(Node<String> root, List<String> nodes) {
        if (root != null) {
            getNodesHelper(root.left, nodes);
            nodes.add(root.data);
            getNodesHelper(root.right, nodes);
        }
    }

    /**
     * Verifica se algum dado está presente na árvore
     * @param data Dado a ser procurado
     * @return Se o dado está presente
     */
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

    /**
     * Remove algum dado da árvore
     * @param data Dado a ser removido
     */
    public void remove(String data) {
        if (search(data)) {
            removeHelper(root, data);
            length--;
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
