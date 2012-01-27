package SyntaxHighlighter;

import java.util.ArrayList;

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
		//result = highlightCppComment( result );
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

	private ArrayList<SyntaxHighlightedLine> highlightCppComment(ArrayList<SyntaxHighlightedLine> lines)
	{
		ArrayList<SyntaxHighlightedLine> result = new ArrayList<SyntaxHighlightedLine>();
		
		for( SyntaxHighlightedLine l: lines )
		{
			String line = l.getContent();
			if (line.contains("//"))
			{
				String before = line.substring(0,line.indexOf("//"));
				String comment = line.substring(line.indexOf("//"));
				result.clear();
				if (before.length()>0)
					result.add( new SyntaxHighlightedLine( SyntaxHighlightedLine.TEXT, before ) );
				result.add( new SyntaxHighlightedLine( SyntaxHighlightedLine.COMMENT, comment ) );				
			}
		}
		return result;
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
	
	private ArrayList<SyntaxHighlightedLine> highlightLine( String line )
	{
		ArrayList<SyntaxHighlightedLine> result = new ArrayList<SyntaxHighlightedLine>();
		result.add( new SyntaxHighlightedLine( SyntaxHighlightedLine.TEXT, line) );
		return result;
		
	}

	public ArrayList<SyntaxHighlightedLine> highlightline(String line) {
		ArrayList<SyntaxHighlightedLine> result = new ArrayList<SyntaxHighlightedLine>();
		
		// Start with 1 part, all text
		result.addAll( highlightLine( line ) );
		
		// Check if there are tokens that need to be replaced in the line
		if (hasComments(result))
			result = highlightCppComment( result );
		
		return result;
	}

	private boolean hasComments(ArrayList<SyntaxHighlightedLine> lines) {
		for( SyntaxHighlightedLine l: lines )
		{
			String line = l.getContent();
			if ( ( l.getText() == SyntaxHighlightedLine.TEXT ) && 
					( ( line.contains("//") ) || ( line.contains("/*") ) || ( line.contains("*/") ) ) )
			{
				return true;
			}
		}
		
		return false;
	}
}
