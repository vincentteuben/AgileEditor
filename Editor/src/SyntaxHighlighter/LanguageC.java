package SyntaxHighlighter;

public class LanguageC implements Language {
	private String[] dataTypes = { "char", "short", "int", "long", "bool",
			"float", "double", "long" };
	private String[] keywords = { "auto", "break", "case", "const", "continue",
			"default", "do", "double", "else", "enum", "extern", "for", "goto",
			"if", "register", "return", "signed", "sizeof", "static", "struct",
			"switch", "typedef", "union", "unsigned", "void", "volatile",
			"while" };
	@Override
	public String[] getDataTypes() {
		return dataTypes;
	}
	@Override
	public String[] getKeywords() {
		return keywords;
	}	
}
