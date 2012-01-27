package SyntaxHighlighter;

public class SyntaxHighlightedLine {

	public static final String TEXT = "text";
	public static final String COMMENT = "comment";

	private String type;
	private String content;
	
	public SyntaxHighlightedLine() {
		this( TEXT, "" );
	}
	
	public SyntaxHighlightedLine( String type, String content ) {
		this.type = type;
		this.content = content;
	}
	
	public String getText() {
		return type;
	}

	public String getContent() {
		return content;
	}

}
