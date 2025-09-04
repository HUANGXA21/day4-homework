import java.util.*;


public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                List<Input> frequencies = countFrequencies(words);
                return composeOutput(frequencies);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private static String composeOutput(List<Input> frequencies) {
        frequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
        StringJoiner result = new StringJoiner("\n");
        for (Input w : frequencies) {
            String s = w.getValue() + " " + w.getWordCount();
            result.add(s);
        }
        return result.toString();
    }

    private List<Input> countFrequencies(String[] words) {
        Map<String, List<String>> groups = groupSameWords(words);
        List<Input> frequencies = new ArrayList<>(groups.size()); // 预先指定容量
        // 直接通过每组的列表大小获取计数
        groups.forEach((word, group) -> frequencies.add(new Input(word, group.size())));
        return frequencies;
    }

    private static Map<String, List<String>> groupSameWords(String[] words) {
        List<String> inputList = new ArrayList<>(Arrays.asList(words));
        //get the map for the next step of sizing the same word
        Map<String, List<String>> map = new HashMap<>();
        for (String input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input)) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input, arr);
            } else {
                map.get(input).add(input);
            }
        }
        return map;
    }
}
