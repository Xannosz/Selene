package hu.xannosz.selene.compiler.tokeniser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hu.xannosz.selene.compiler.tokeniser.rows.Row;
import hu.xannosz.selene.lang.words.Constants;

public class FileTokeniser {

	public static final List<Row> ROWS = Arrays.asList();
	private String file;
	private List<Token> tokens = new ArrayList<>();
	private List<Row> rows = new ArrayList<>();

	public FileTokeniser(String file) {
		this.file = file;
	}

	public void phase1() {
		String puffer = "";
		String mode = "normal";
		for (int i = 0; i < file.length();) {
			switch (mode) {
			case "normal":
				boolean next = false;
				for (String op : Constants.DOUBLE_OPERATORS) {
					if (file.length() != (i + 1) && file.substring(i, i + 2).equals(op)) {
						getToken(puffer);
						puffer = "";
						tokens.add(new Token(op, TokenType.OPERATOR));
						i += 2;
						next = true;
						break;
					}
				}
				if (next) {
					continue;
				}
				for (String op : Constants.SIMPLE_OPERATORS) {
					if (file.substring(i, i + 1).equals(op)) {
						getToken(puffer);
						puffer = "";
						tokens.add(new Token(op, TokenType.OPERATOR));
						i++;
						next = true;
						break;
					}
				}
				if (next) {
					continue;
				}
				for (String se : Constants.SEPARATORS) {
					if (file.substring(i, i + 1).equals(se)) {
						getToken(puffer);
						puffer = "";
						tokens.add(new Token(se, TokenType.SEPARATOR));
						i++;
						next = true;
						break;
					}
				}
				if (next) {
					continue;
				}
				for (String sp : Constants.WHITESPACES) {
					if (file.substring(i, i + 1).equals(sp)) {
						getToken(puffer);
						puffer = "";
						i++;
						next = true;
						break;
					}
				}
				if (next) {
					continue;
				}
				if (file.substring(i, i + 1).equals("'")) {
					getToken(puffer);
					puffer = "";
					mode = "comment";
					i++;
					continue;
				}
				if (file.substring(i, i + 1).equals("\"")) {
					getToken(puffer);
					puffer = "";
					mode = "string";
					i++;
					continue;
				}

				puffer += file.substring(i, i + 1);
				i++;

				break;
			case "string":
				if (file.substring(i, i + 2).equals("\\\"")) {
					puffer += "\"";
					i += 2;
				} else if (file.substring(i, i + 1).equals("\"")) {
					tokens.add(new Token(puffer, TokenType.LITERAL_STRING));
					puffer = "";
					i++;
					mode = "normal";
				} else {
					puffer += file.substring(i, i + 1);
					i++;
				}
				break;
			case "comment":
				if (file.substring(i, i + 2).equals("\\'")) {
					puffer += "'";
					i += 2;
				} else if (file.substring(i, i + 1).equals("'")) {
					tokens.add(new Token(puffer, TokenType.COMMENT));
					puffer = "";
					i++;
					mode = "normal";
				} else {
					puffer += file.substring(i, i + 1);
					i++;
				}
				break;
			}
		}
		getToken(puffer);
	}

	private void getToken(String token) {
		if (token.equals("")) {
			return;
		}
		for (String word : Constants.KEY_WORDS) {
			if (token.equals(word)) {
				tokens.add(new Token(token, TokenType.KEY_WORD));
				return;
			}
		}
		for (String word : Constants.KEY_WORDS_MODIFIERS) {
			if (token.equals(word)) {
				tokens.add(new Token(token, TokenType.KEY_WORDS_MODIFIER));
				return;
			}
		}
		for (String word : Constants.SKOPES) {
			if (token.equals(word)) {
				tokens.add(new Token(token, TokenType.SKOPE));
				return;
			}
		}
		for (String word : Constants.VARIABLES) {
			if (token.equals(word)) {
				tokens.add(new Token(token, TokenType.VARIABLE));
				return;
			}
		}
		for (String word : Constants.TYPES) {
			if (token.equals(word)) {
				tokens.add(new Token(token, TokenType.TYPE));
				return;
			}
		}
		if (token.matches("[0-9]+[f]?")) {
			tokens.add(new Token(token, TokenType.LITERAL_NUMBER));
			return;
		}
		if (token.equals("true") || token.equals("false")) {
			tokens.add(new Token(token, TokenType.LITERAL_BOOLEAN));
			return;
		}
		tokens.add(new Token(token, TokenType.IDENTIFIER));
	}

	public void phase2() {
		identifyNumbers();
		rows = parseRows(tokens);
	}

	private void identifyNumbers() {
		
	}

	public static List<Row> parseRows(List<Token> tokens) {
		List<Row> response = new ArrayList<>();
		for (int i = 0; i < tokens.size();) {
			boolean warp = false;
			for (int e = i+1; e < tokens.size(); e++) {
				List<Token> tokenSublist = new ArrayList<>();
				for (int f = i; f <= e; f++) {
					tokenSublist.add(tokens.get(f));
				}
				for (Row row : ROWS) {
					Row result = row.matcher(tokenSublist);
					if (result != null) {
						response.add(result);
						warp = true;
						break;
					}
				}
				if(warp){
					i=e;
					break;
				}
			}
		}
		return response;
	}

	public String showTokens() {
		String result = "";
		for (Token token : tokens) {
			result += "[" + token.syntax + "#" + token.type.toString() + "]";
		}
		return result;
	}
	
	public String showRows() {
		String result = "";
		for (Row row : rows) {
			result += "{" + row.toString() + "}";
		}
		return result;
	}
}
