public class Input {
    private String word;
    private int count;

    public Input(String value, int count){
        this.word =value;
        this.count =count;
    }


    public String getValue() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }
}
