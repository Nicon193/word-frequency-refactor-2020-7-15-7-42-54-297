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
                Map<String, List<wordInfo>> wordListMap =getListMap(wordInfoList);

                List<wordInfo> tempWordInfos = new ArrayList<>();
                for (Map.Entry<String, List<wordInfo>> entry : wordListMap.entrySet()) {
                    wordInfo wordInfo = new wordInfo(entry.getKey(), entry.getValue().size());
                    tempWordInfos.add(wordInfo);
                }
                wordInfoList = tempWordInfos;

                wordInfoList.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

                StringJoiner frequencyWord = new StringJoiner(LINE_BREAK_DELIMITER);
                for (wordInfo w : wordInfoList) {
                    String s = w.getWord() + " " +w.getWordCount();
                    frequencyWord.add(s);
                }
                return frequencyWord.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<wordInfo>> getListMap(List<wordInfo> wordInfoList) {
        Map<String, List<wordInfo>> map = new HashMap<>();
        for (wordInfo wordInfo : wordInfoList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getWord(), arr);
            }
            else {
                map.get(wordInfo.getWord()).add(wordInfo);
            }
        }
        return map;
    }
}
