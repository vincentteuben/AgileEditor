package SyntaxHighlighter;
public class SyntaxHighlighter {
	private Language language;
	
	public SyntaxHighlighter(Language scheme ) {
		this.language = scheme;
	}

	public String highlight(String line) {
		String result = line;
		
		result = highlightComments(result);		
		result = highlightTypes(result, language.getDataTypes() );
		
		return result;
	}

	private String highlightTypes(String result, String[] types) {
		for( String type: types )
			result = highlightType( result, type );
		return result;
	}

	private String highlightComments(String result) {
		result = highlightCppComment( result );
		result = highlightCComment( result );
		return result;
	}
	
	private String highlightCComment( String line )
	{
		if (line.contains("/*") && line.contains("*/"))
		{
			String before = line.substring(0,line.indexOf("/*"));
			String comment = line.substring(line.indexOf("/*"), line.indexOf("*/") + 2);
			String after = line.substring(line.indexOf("*/") + 2);

			line = ( before + "<comment>" + comment + "</comment>" + after );
		}

		return line;
	}

	private String highlightCppComment(String line)
	{
		if (line.contains("//"))
		{
			String before = line.substring(0,line.indexOf("//"));
			String comment = line.substring(line.indexOf("//"));
			line = ( before + "<comment>" + comment + "</comment>" );
		}

		return line;
	}
	
	private String highlightType( String line, String type )
	{
		if (line.contains(type))
		{
			int startPos = line.indexOf(type);
			int endPos = startPos + type.length();
			line = "<type>" + line.substring(startPos, endPos) + "</type>" + line.substring(endPos);
		}

		return line;	
	}

}
