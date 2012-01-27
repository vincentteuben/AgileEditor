package SyntaxHighlighter;

public class LanguageC implements Language {
	String[] dataTypes = { "unsigned", "char", "short", "int", "long", "bool",
			"float", "double", "long" };
	
	public String[] getDataTypes() {
		return dataTypes;
	}
}
