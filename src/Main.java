import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {

        String text = """
                Good programming is not learned from
                generalities, but by seeing how significant
                programs can be made clean, easy to
                read, easy to maintain and modify,
                human-engineered, efficient, and reliable,
                by the application of common sense and
                by the use of good programming practices
                """;
        BinarySearchTreeString tree = new BinarySearchTreeString();
        // TODO: implementar a minha propria hash table
        HashMap<String, ArrayList<Integer>> keywords = new HashMap();

        //TODO: criar essa lista automaticamente a partir de um txt
        keywords.put("programming", new ArrayList<>());
        keywords.put("programs", new ArrayList<>());
        keywords.put("easy", new ArrayList<>());
        keywords.put("by", new ArrayList<>());
        keywords.put("human-engineered", new ArrayList<>());
        keywords.put("and", new ArrayList<>());
        keywords.put("be", new ArrayList<>());
        keywords.put("to", new ArrayList<>());

        AtomicInteger lineCounter = new AtomicInteger();
        Arrays.stream(text.split("\\r?\\n")).forEach(line -> {
            String[] words = line.split("[\\s,.;?!/\\\\_{}\\[\\]^'\"+]+");

            for (String word : words) {
                // achou uma key no hash
                if (keywords.get(word) != null) {
                    // adiciona a linha que a palavra se encontra no valor do hash na posição da key
                    keywords.get(word).add(lineCounter.intValue() + 1);
                    tree.insert(new Node<>(word));
                }
            }
            lineCounter.getAndIncrement();
        });

        List<String> keysInOrder = tree.getNodesAlphabetically();

        for (int i = 0; i < keysInOrder.size(); i++) {
            String currentKey = keysInOrder.get(i);
            ArrayList<Integer> keylines = keywords.get(currentKey);
            StringBuilder str = new StringBuilder();
            str.append(currentKey).append(" ");

            for (int j = 0; j < keylines.size(); j++) {
                str.append(keylines.get(j));
                if (j + 1 != keylines.size())
                    str.append(" ");
            }
            System.out.println(str);
        }
    }
}