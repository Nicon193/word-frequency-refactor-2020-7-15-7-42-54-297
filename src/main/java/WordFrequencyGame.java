import java.util.*;

public class WordFrequencyGame {

    private final String SPACE_PATTERN = "\\s+";
    private final String LINE_BREAK_DELIMITER = "\n";
    private final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String senstence) {

        try {
            List<WordInfo> wordInfoList = computeWordCount(senstence);
            sortWordInfoListByBesc(wordInfoList);
            return generateFrequencyWord(wordInfoList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }


    private List<WordInfo> computeWordCount(String senstence) {

        List<WordInfo> WordInfos = new ArrayList<>();
        List<String> words = Arrays.asList(senstence.split(SPACE_PATTERN));

        for (String theWord : new HashSet<>(words)) {
            int count = (int) words.stream().filter(word -> word.equals(theWord)).count();
            WordInfos.add(new WordInfo(theWord, count));
        }
        return WordInfos;
    }


    private void sortWordInfoListByBesc(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
    }

    public String generateFrequencyWord(List<WordInfo> WordInfos) {

        StringJoiner frequencyWord = new StringJoiner(LINE_BREAK_DELIMITER);
        for (WordInfo wordInfo : WordInfos) {
            String result = wordInfo.getWord() + " " + wordInfo.getWordCount();
            frequencyWord.add(result);
        }
        return frequencyWord.toString();
    }

}
