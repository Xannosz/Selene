package hu.xannosz.selene.lang.words;

import java.util.Arrays;
import java.util.List;

public class Constants {
	public static final List<String> DOUBLE_OPERATORS = Arrays.asList("::", "||", "&&", "==", "<=", ">=", "!=");
	public static final List<String> SIMPLE_OPERATORS = Arrays.asList("+", "-", "*", "/", "\\", ":", "|", "&", "#", "!",
			"~", "^", "?", "=", "<", ">", "%", ".");
	public static final List<String> SEPARATORS = Arrays.asList("{", "}", "[", "]", "(", ")", ";", "@", "$");
	public static final List<String> KEY_WORDS = Arrays.asList("import", "null", "nil", "meta", "selene", "this",
			"return", "as", "if", "else", "switch", "case", "while", "until", "for", "extends", "uses", "implements");
	public static final List<String> KEY_WORDS_MODIFIERS = Arrays.asList("static", "final", "abstract", "dynamic");
	public static final List<String> SKOPES = Arrays.asList("universal", "global", "public", "private", "protected");
	public static final List<String> VARIABLES = Arrays.asList("string", "int", "float", "bool", "void", "table");
	public static final List<String> TYPES = Arrays.asList("function", "listener", "method", "class", "metric",
			"interface", "cube", "evente");
	public static final List<String> WHITESPACES = Arrays.asList(" ", "\n", "\t", "\r");
}
