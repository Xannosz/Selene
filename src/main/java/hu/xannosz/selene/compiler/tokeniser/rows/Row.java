package hu.xannosz.selene.compiler.tokeniser.rows;

import java.util.List;

import hu.xannosz.selene.compiler.tokeniser.Token;

public abstract class Row {
	public abstract Row matcher(List<Token> tokenSublist);
}
