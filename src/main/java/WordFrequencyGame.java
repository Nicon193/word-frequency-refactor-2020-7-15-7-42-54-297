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

                String[] words = senstence.split(SPACE_PATTERN);

                List<wordInfo> wordInfoList = new ArrayList<>();
                for (String word : words) {
                    wordInfo wordInfo = new wordInfo(word, 1);
                    wordInfoList.add(wordInfo);
                }

                Map<String, List<wordInfo>> wordListMap =getListMap(wordInfoList);

                List<wordInfo> tempWordInfos = new ArrayList<>();
                for (Map.Entry<String, List<wordInfo>> entry : wordListMap.entrySet()) {
                    wordInfo wordInfo = new wordInfo(entry.getKey(), entry.getValue().size());
                    tempWordInfos.add(wordInfo);
                }
                wordInfoList = tempWordInfos;

                wordInfoList.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

                StringJoiner frequencyWord = new StringJoiner(LINE_BREAK_DELIMITER);
                for (wordInfo wordInfo : wordInfoList) {
                    String result = wordInfo.getWord() + " " +wordInfo.getWordCount();
                    frequencyWord.add(result);
                }
                return frequencyWord.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<wordInfo>> getListMap(List<wordInfo> wordInfoList) {
        Map<String, List<wordInfo>> wordListMap = new HashMap<>();
        for (wordInfo wordInfo : wordInfoList){
            if (!wordListMap.containsKey(wordInfo.getWord())) {
                ArrayList wordInfos = new ArrayList<>();
                wordInfos.add(wordInfo);
                wordListMap.put(wordInfo.getWord(), wordInfos);
            }
            else {
                wordListMap.get(wordInfo.getWord()).add(wordInfo);
            }
        }
        return wordListMap;
    }
}
