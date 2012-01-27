package SyntaxHighlighter;

public class CodePart {

	public static final String TEXT = "text";
	public static final String COMMENT = "comment";
	public static final String DATATYPE = "datatype";
	public static final String KEYWORD = "keyword";

	private String datatype;
	private String content;
	
	public CodePart() {
		this( TEXT, "" );
	}
	
	public CodePart( String datatype, String content ) {
		this.datatype = datatype;
		this.content = content;
	}
	
	public String getDataType() {
		return datatype;
	}

	public String getContent() {
		return content;
	}
	
	public boolean equals( CodePart cp ) {
		return ( getDataType() == cp.getContent() ) && ( getContent() == cp.getContent() );
	}

}
