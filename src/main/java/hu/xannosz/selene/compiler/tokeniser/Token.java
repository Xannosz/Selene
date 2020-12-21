package hu.xannosz.selene.compiler.tokeniser;

public class Token {
	public final String syntax;
	public final TokenType type;

	public Token(String syntax, TokenType type) {
		this.syntax = syntax;
		this.type = type;
	}
}
