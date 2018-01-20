package hu.xannosz.selene.core.tokens;

public class WordToken implements Token {
    private String value;

    public WordToken(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "[Value: " + value + "]";
    }
}
