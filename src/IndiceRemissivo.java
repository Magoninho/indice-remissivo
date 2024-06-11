import HashTable.TabelaHash;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class IndiceRemissivo {

    private final BinarySearchTreeString tree = new BinarySearchTreeString();
    private final TabelaHash<String, ListaDuplamenteEncadeada> keywords = new TabelaHash<>();
    private String text = null;

    public IndiceRemissivo() {
        this.text = getStringFromFile("src/resources/text.txt");
    }

    /**
     * Esse método inicializa a HashTable dos keywords, inserindo, como chave, a string da palavra, e como valor, uma
     * Lista Duplamente Encadeada vazia (para ser preenchida depois).
     */
    private void populateKeywords() {
        List<String> keywords = getLinesFromFile("src/resources/keywords.txt");
        for (String key : keywords) {
            this.keywords.add(key, new ListaDuplamenteEncadeada());
        }
    }

    /**
     * Esse método serve para encontrar e separar as palavras encontradas no texto. E, logo em seguida, adicionar na
     * LinkedList da HashTable uma nova posição em que a palavra foi encontrada. Depois, finalmente, é armazenado, então,
     * em uma Árvore Binária de Busca, que será utilizada para prevenir entradas duplicadas e facilitar a impressão em
     * ordem alfabética.
     */
    private void findWords() {
        AtomicInteger lineCounter = new AtomicInteger();
        Arrays.stream(text.split("\\r?\\n")).forEach(line -> {
            // Regex para aceitar espaços e pontuações para separar palavras
            String[] words = line.split("[\\s,.;?!/\\\\_{}\\[\\]^'\"+]+");

            for (String word : words) {
                // Achou uma key no hash
                if (keywords.get(word) != null) {
                    // Adiciona a linha que a palavra se encontra no valor do hash na posição da key
                    keywords.get(word).insereFinal(lineCounter.intValue() + 1);
                    tree.insert(new Node<>(word));
                }
            }
            lineCounter.getAndIncrement();
        });
    }

    /**
     * Esse método imprime o indice remissivo no arquivo output.txt
     */
    private void printToFile() {
        List<String> keysInOrder = tree.getNodesAlphabetically();
        Path filePath = Paths.get("src/resources/output.txt");

        // Limpando o arquivo antes de escrever novo conteúdo
        try {
            Files.newBufferedWriter(filePath , StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Escrevendo palavras encontradas no arquivo
        for (int i = 0; i < keysInOrder.size(); i++) {
            String currentKey = keysInOrder.get(i);
            ListaDuplamenteEncadeada keylines = keywords.get(currentKey);

            String str = currentKey + " " +
                    keylines.getInlineList();

            try (FileWriter fw = new FileWriter(String.valueOf(filePath), true);
                 PrintWriter pw = new PrintWriter(fw)) {

                pw.println(str);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void print() {
        List<String> keysInOrder = tree.getNodesAlphabetically();

        for (int i = 0; i < keysInOrder.size(); i++) {
            String currentKey = keysInOrder.get(i);
            ListaDuplamenteEncadeada keylines = keywords.get(currentKey);

            String str = currentKey + " " +
                    keylines.getInlineList();

            System.out.println(str);
        }
    }

    /**
     * Esse método recebe um caminho para um arquivo e retorna uma lista em que cada elemento é uma linha do conteúdo
     * do arquivo.
     * @param path Caminho do arquivo
     * @return Conteúdo do arquivo
     */
    private List<String> getLinesFromFile(String path) {
        Path filePath = Paths.get(path);
        List<String> lines;

        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    /**
     * Esse método recebe um caminho para um arquivo e retorna o seu conteúdo em forma de uma String
     * @param path Caminho do arquivo
     * @return Conteúdo do arquivo
     */
    private String getStringFromFile(String path) {
        List<String> lines = getLinesFromFile(path);
        StringBuilder text = new StringBuilder();

        for (String line : lines) {
            text.append(line).append("\n");
        }

        return text.toString();
    }

    /**
     * O método init resume o comportamento dessa classe
     */
    public void init() {
        populateKeywords();
        findWords();
        print();
        printToFile();
    }
}
