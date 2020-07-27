import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private final String SPACE_PATTERN= "\\s+";
    private final String LINE_BREAK_DELIMITER = "\n";
    private final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String senstence) {


        if (senstence.split(SPACE_PATTERN).length==1) {
            return senstence + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = senstence.split(SPACE_PATTERN);

                List<wordInfo> wordInfoList = new ArrayList<>();
                for (String word : words) {
                    wordInfo wordInfo = new wordInfo(word, 1);
                    wordInfoList.add(wordInfo);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<wordInfo>> map =getListMap(wordInfoList);

                List<wordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<wordInfo>> entry : map.entrySet()) {
                    wordInfo wordInfo = new wordInfo(entry.getKey(), entry.getValue().size());
                    list.add(wordInfo);
                }
                wordInfoList = list;

                wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(LINE_BREAK_DELIMITER);
                for (wordInfo w : wordInfoList) {
                    String s = w.getValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<wordInfo>> getListMap(List<wordInfo> wordInfoList) {
        Map<String, List<wordInfo>> map = new HashMap<>();
        for (wordInfo wordInfo : wordInfoList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            }
            else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
