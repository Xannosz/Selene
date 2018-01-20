package hu.xannosz.selene.core;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final List<String> withSpaces = Arrays.asList(" ", "\n", "\t");
    public static final List<String> tokenBorders = Arrays.asList(" ", "=", "+=","-=","(",")",";");
    public static final List<String> closedWords = Arrays.asList("import", "function", "if","else","try","catch");
}
