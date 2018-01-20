package hu.xannosz.selene.core.tokens;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.common.annotations.VisibleForTesting;

import hu.xannosz.selene.core.Constants;
import hu.xannosz.selene.utils.Pair;

public class Tokeniser {

    private String content = "";
    private int cursor = 0;
    private int stage = 0;
    @VisibleForTesting
    protected List<Token> tokens = new ArrayList<>();

    public void parseFile(Path path) {
        try {
            Files.readAllLines(path).forEach((str) -> {
                content += str;
            });
        } catch (IOException e) {
            e.printStackTrace(); // TODO
        }

    }

    public void addContent(String content) {
        this.content = content;
    }
    
    @VisibleForTesting
    protected void parse() {
        while(cursor < content.length()){
            parseOne();
        }
        rowing();
    }

    private void rowing() {

    }

    @VisibleForTesting
    protected void parseOne() {
        Pair<String, String> response = readWord();
        if (!response.getLeft().isEmpty()) {
            if (isClosedWord(response.getLeft())) {
                tokens.add(new WordToken(response.getLeft()));
            } else {
                tokens.add(new VariableToken(response.getLeft()));
            }
        }
        if (!isWitheSpace(response.getRight())) {
            tokens.add(new WordToken(response.getRight()));
        }
    }

    private boolean isWitheSpace(String space) {
        for (String withSpace : Constants.withSpaces) {
            if (withSpace.equals(space))
                return true;
        }
        return false;
    }

    private boolean isClosedWord(String word) {
        for (String closedWord : Constants.closedWords) {
            if (closedWord.equals(word))
                return true;
        }
        return false;
    }

    @VisibleForTesting
    protected Pair<String, String> readWord() {
        int index = -1;
        String nextBorder = null;
        for (String border : Constants.tokenBorders) {
            int i = content.indexOf(border, cursor);
            if (i != -1 && (i < index || index == -1)) {
                index = i;
                nextBorder = border;
            }
        }
        String response = content.substring(cursor, index);
        cursor = index + 1;
        return new Pair(response, nextBorder);
    }
}
