package SyntaxHighlighter;

public class CodePart {

	public static final String TEXT = "text";
	public static final String COMMENT = "comment";
	public static final String DATATYPE = "datatype";

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

}
