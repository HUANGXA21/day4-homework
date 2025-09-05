import java.util.*;
import java.util.stream.Collectors;


public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        } else {
            try {
                List<InputFrequency> frequencies = countFrequencies(words);
                return composeOutput(frequencies);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private static String composeOutput(List<InputFrequency> frequencies) {
        return frequencies.stream()
                .sorted((w1, w2) -> Integer.compare(w2.getWordCount(), w1.getWordCount()))
                .map(w -> w.getValue() + " " + w.getWordCount())
                .collect(Collectors.joining("\n"));
    }

    private List<InputFrequency> countFrequencies(String[] words) {
        Map<String, List<String>> groups = groupSameWords(words);
        List<InputFrequency> frequencies = new ArrayList<>(groups.size());
        // 直接通过每组的列表大小获取计数
        groups.forEach((word, group) -> frequencies.add(new InputFrequency(word, group.size())));
        return frequencies;
    }

    private static Map<String, List<String>> groupSameWords(String[] words) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String input : words) {
            // 若key不存在则创建新列表，然后向列表中添加元素
            groups.computeIfAbsent(input, k -> new ArrayList<>()).add(input);
        }
        return groups;
    }
}
