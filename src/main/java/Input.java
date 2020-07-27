public class Input {
    private String word;
    private int count;

    public Input(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getValue() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }
}
