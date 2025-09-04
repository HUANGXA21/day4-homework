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
                //split the input string with 1 to n pieces of spaces
                List<InputFrequency> frequencies = countFrequencies(words);
                return composeOutput(frequencies);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private static String composeOutput(List<InputFrequency> frequencies) {
        return frequencies.stream()
                // 按词频降序排序
                .sorted((w1, w2) -> Integer.compare(w2.getWordCount(), w1.getWordCount()))
                // 转换为"单词 次数"格式的字符串
                .map(w -> w.getValue() + " " + w.getWordCount())
                // 用换行符连接所有字符串
                .collect(Collectors.joining("\n"));
    }

    private List<InputFrequency> countFrequencies(String[] words) {
        Map<String, List<String>> groups = groupSameWords(words);
        List<InputFrequency> frequencies = new ArrayList<>(groups.size()); // 预先指定容量
        // 直接通过每组的列表大小获取计数
        groups.forEach((word, group) -> frequencies.add(new InputFrequency(word, group.size())));
        return frequencies;
    }

    private static Map<String, List<String>> groupSameWords(String[] words) {
        List<String> inputList = new ArrayList<>(Arrays.asList(words));
        //get the map for the next step of sizing the same word
        Map<String, List<String>> groups = new HashMap<>();
        for (String input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!groups.containsKey(input)) {
                List<String> arr = new ArrayList<>();
                arr.add(input);
                groups.put(input, arr);
            } else {
                groups.get(input).add(input);
            }
        }
        return groups;
    }
}
