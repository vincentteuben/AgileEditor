package SyntaxHighlighter;

import java.util.ArrayList;

public class SyntaxHighlighter {
	private Language language;
	
	public SyntaxHighlighter(Language scheme ) {
		this.language = scheme;
	}

	private ArrayList<CodePart> highlightDataTypes(ArrayList<CodePart> lines, String[] types) {
		ArrayList<CodePart> result = new ArrayList<CodePart>();
		
		for( CodePart l: lines )
		{	
			if ( hasType(l, types))
			{
				for( String type: types )
					result.addAll( highlightDataType( l, type ) );
			}
			else
				result.add(l);
		}
		
		return result;
	}

	private ArrayList<CodePart> highlightKeywords(ArrayList<CodePart> lines, String[] keywords) {
		ArrayList<CodePart> result = new ArrayList<CodePart>();
		
		for( CodePart l: lines )
		{	
			if ( hasType(l, keywords))
			{
				for( String type: keywords )
					result.addAll( highlightKeywords( l, type ) );
			}
			else
				result.add(l);
		}
		
		return result;
	}
	
	private ArrayList<CodePart> highlightComments(ArrayList<CodePart> line) {
		ArrayList<CodePart> result = new ArrayList<CodePart>();
		result.addAll(line);
		
		if (hasCComments(result))
			result = highlightCComment( result );
		if (hasCppComments(result))
			result = highlightCppComment( result );
		
		return result;
	}
	
	private ArrayList<CodePart> highlightCComment( ArrayList<CodePart> lines )
	{
		ArrayList<CodePart> result = new ArrayList<CodePart>();
		
		for( CodePart l: lines )
		{	
			String line = l.getContent();
			if (line.contains("/*") && (line.contains("*/")))
			{
				String before = line.substring(0,line.indexOf("/*"));
				String comment = line.substring(line.indexOf("/*"), line.indexOf("*/") + 2);
				String after = line.substring(line.indexOf("*/") + 2);
				
				if (before.length()>0)
					result.add( new CodePart( CodePart.TEXT, before ) );

				result.add( new CodePart( CodePart.COMMENT, comment ) );				
				
				if (after.length()>0)
					result.add( new CodePart( CodePart.TEXT, after ) );
			}
		}
		return result;
	}

	private ArrayList<CodePart> highlightCppComment(ArrayList<CodePart> lines)
	{
		ArrayList<CodePart> result = new ArrayList<CodePart>();
		
		for( CodePart l: lines )
		{
			String line = l.getContent();
			if (line.contains("//"))
			{
				String before = line.substring(0,line.indexOf("//"));
				String comment = line.substring(line.indexOf("//"));
				result.clear();
				if (before.length()>0)
					result.add( new CodePart( CodePart.TEXT, before ) );
				result.add( new CodePart( CodePart.COMMENT, comment ) );				
			}
		}
		return result;
	}
	
	private ArrayList<CodePart> highlightDataType( CodePart l, String type )
	{
		ArrayList<CodePart> result = new ArrayList<CodePart>();

			String line = l.getContent();
			if (line.contains(type))
			{
				String before = line.substring(0,line.indexOf(type));
				String datatype = line.substring(line.indexOf(type), line.indexOf(type)+type.length());
				String after = line.substring(line.indexOf(type)+type.length());
				if (before.length()>0)
					result.add( new CodePart( CodePart.TEXT, before));
				if (datatype.length()>0)
					result.add( new CodePart( CodePart.DATATYPE, datatype));
				if (after.length()>0)
					result.add( new CodePart( CodePart.TEXT, after));
			}

		return result;	
	}
	
	private ArrayList<CodePart> highlightKeywords( CodePart l, String keyword )
	{
		ArrayList<CodePart> result = new ArrayList<CodePart>();

			String line = l.getContent();
			if (line.contains(keyword))
			{
				String before = line.substring(0,line.indexOf(keyword));
				String datatype = line.substring(line.indexOf(keyword), line.indexOf(keyword)+keyword.length());
				String after = line.substring(line.indexOf(keyword)+keyword.length());
				if (before.length()>0)
					result.add( new CodePart( CodePart.TEXT, before));
				if (datatype.length()>0)
					result.add( new CodePart( CodePart.KEYWORD, datatype));
				if (after.length()>0)
					result.add( new CodePart( CodePart.TEXT, after));
			}

		return result;	
	}

	public ArrayList<CodePart> highlight(String line) {
		ArrayList<CodePart> result = new ArrayList<CodePart>();
		
		// Start with 1 part, all text
		result.add( new CodePart( CodePart.TEXT, line) );

		result = highlightComments(result);
		result = highlightDataTypes(result, language.getDataTypes());
		result = highlightKeywords( result, language.getKeywords());
		
		return result;
	}

	private boolean hasCComments(ArrayList<CodePart> lines) {
		for( CodePart l: lines )
		{
			String line = l.getContent();
			if ( ( l.getDataType() == CodePart.TEXT ) && 
					( ( line.contains("/*") ) && ( line.contains("*/") ) ) )
			{
				return true;
			}
		}
		
		return false;
	}

	private boolean hasCppComments(ArrayList<CodePart> lines) {
		for( CodePart l: lines )
		{
			String line = l.getContent();
			if ( ( l.getDataType() == CodePart.TEXT ) && 
					( line.contains("//") ) )
			{
				return true;
			}
		}
		
		return false;
	}

	private boolean hasType(CodePart l, String[] types) {
		for (String t: types) {
			if (l.getContent().contains(t))
				return true;
		}
		return false;
	}
}
