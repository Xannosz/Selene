package hu.xannosz.selene.tyho;

import hu.xannosz.selene.compiler.tokeniser.FileTokeniser;

public class Main {
	public static void main(String[] args) {
		FileTokeniser fileTokeniser = new FileTokeniser("a\nb\tc\n\td  false ||  ' komment sdf 34 \\' 7f \"komment\" ' \"jos\\\"ka\" 7f 9ff 7.0 e/g*t*6");
		fileTokeniser.phase1();
		System.out.println(fileTokeniser.showTokens());
	}
}
