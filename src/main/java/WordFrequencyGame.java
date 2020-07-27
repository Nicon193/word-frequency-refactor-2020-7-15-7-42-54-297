import java.util.*;

public class WordFrequencyGame {

    private final String SPACE_PATTERN= "\\s+";
    private final String LINE_BREAK_DELIMITER = "\n";
    private final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String senstence) {

//        if (senstence.split(SPACE_PATTERN).length==1) {
//            return senstence + " 1";
//        } else {
//
//            try {
//
//                String[] words = senstence.split(SPACE_PATTERN);
//
//                List<wordInfo> wordInfoList = new ArrayList<>();
//                for (String word : words) {
//                    wordInfo wordInfo = new wordInfo(word, 1);
//                    wordInfoList.add(wordInfo);
//                }
//
//                Map<String, List<wordInfo>> wordListMap =getListMap(wordInfoList);
//
//                List<wordInfo> tempWordInfos = new ArrayList<>();
//                for (Map.Entry<String, List<wordInfo>> entry : wordListMap.entrySet()) {
//                    wordInfo wordInfo = new wordInfo(entry.getKey(), entry.getValue().size());
//                    tempWordInfos.add(wordInfo);
//                }
//                wordInfoList = tempWordInfos;
        try {

                List<wordInfo> wordInfoList = computeWordCount(senstence);
                sortWordInfoListByBesc(wordInfoList);
                return generateFrequencyWord(wordInfoList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }


    private List<wordInfo> computeWordCount(String senstence){

        List<wordInfo> wordInfos = new ArrayList<>();
        List<String> words = Arrays.asList(senstence.split(SPACE_PATTERN));

        for (String theWord: new HashSet<>(words)) {
            int count = (int)words.stream().filter(word -> word.equals(theWord)).count();
            wordInfos.add(new wordInfo(theWord, count));
        }

        return wordInfos;
    }


    private void sortWordInfoListByBesc(List<wordInfo> wordInfoList){
        wordInfoList.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
    }

    public String generateFrequencyWord(List<wordInfo> wordInfos){

        StringJoiner frequencyWord = new StringJoiner(LINE_BREAK_DELIMITER);
        for (wordInfo wordInfo : wordInfos) {
            String result = wordInfo.getWord() + " " +wordInfo.getWordCount();
            frequencyWord.add(result);
        }
        return frequencyWord.toString();
    }

}
