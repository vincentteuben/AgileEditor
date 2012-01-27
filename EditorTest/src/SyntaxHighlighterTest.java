import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SyntaxHighlighter.CodePart;
import SyntaxHighlighter.LanguageC;
import SyntaxHighlighter.SyntaxHighlighter;

public class SyntaxHighlighterTest {

	private SyntaxHighlighter syntax;

	@Before
	public void setUp() throws Exception {
		syntax = new SyntaxHighlighter( new LanguageC() );
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_get_a_line_without_highlighting() {
		ArrayList<CodePart> line = syntax.highlight("this is a test");
		assertEquals( 1, line.size() );
		assertEquals( CodePart.TEXT, line.get(0).getDataType() );
		assertEquals( "this is a test", line.get(0).getContent() );		
	}

	@Test
	public void test_cpp_style_comment_highlighting() {
		ArrayList<CodePart> line = syntax.highlight("// This is a comment");
		assertEquals( 1, line.size() );
		assertEquals( CodePart.COMMENT, line.get(0).getDataType() );
		assertEquals( "// This is a comment", line.get(0).getContent() );		
	}
	
	@Test
	public void test_cpp_style_comment_after_code_highlighting() {
		ArrayList<CodePart> line = syntax.highlight("asdf //foo");
		assertEquals( 2, line.size() );
		assertEquals( CodePart.TEXT, line.get(0).getDataType() );
		assertEquals( "asdf ", line.get(0).getContent() );		
		assertEquals( CodePart.COMMENT, line.get(1).getDataType() );
		assertEquals( "//foo", line.get(1).getContent() );		
	}
	
	// TODO: I was here...
	@Test
	public void test_c_style_comment_highlighting() {
		ArrayList<CodePart> line = syntax.highlight("/* test */");
		assertEquals( 1, line.size() );
		assertEquals( CodePart.COMMENT, line.get(0).getDataType() );
		assertEquals( "/* test */", line.get(0).getContent() );		
	}
	
	@Test
	public void test_c_style_comment_in_line_highlighting() {
		ArrayList<CodePart> line = syntax.highlight("a = /*10*/11;");
		assertEquals( 3, line.size() );
		assertEquals( CodePart.TEXT, line.get(0).getDataType() );
		assertEquals( "a = ", line.get(0).getContent() );		
		assertEquals( CodePart.COMMENT, line.get(1).getDataType() );
		assertEquals( "/*10*/", line.get(1).getContent() );		
		assertEquals( CodePart.TEXT, line.get(2).getDataType() );
		assertEquals( "11;", line.get(2).getContent() );		
}
	
	@Test
	public void test_get_a_type_highlighting() {
		ArrayList<CodePart> line = syntax.highlight("int i;");
		assertEquals( 2, line.size() );
		assertEquals( CodePart.DATATYPE, line.get(0).getDataType() );
		assertEquals( "int", line.get(0).getContent() );		
		assertEquals( CodePart.TEXT, line.get(1).getDataType() );
		assertEquals( " i;", line.get(1).getContent() );		

		line = syntax.highlight("short s = 0;");
		assertEquals( 2, line.size() );
		assertEquals( CodePart.DATATYPE, line.get(0).getDataType() );
		assertEquals( "short", line.get(0).getContent() );		
		assertEquals( CodePart.TEXT, line.get(1).getDataType() );
		assertEquals( " s = 0;", line.get(1).getContent() );		
	}
	
	@Test
	public void test_a_keyword_highlighting() {
		ArrayList<CodePart> line = syntax.highlight("return i;");
		assertEquals( 2, line.size() );
		assertEquals( CodePart.KEYWORD, line.get(0).getDataType() );
		assertEquals( "return", line.get(0).getContent() );		
		assertEquals( CodePart.TEXT, line.get(1).getDataType() );
		assertEquals( " i;", line.get(1).getContent() );		
	}
	
	@Test
	public void acceptence_test_a_line_of_code() {
		ArrayList<CodePart> line = syntax.highlight("for( int i=0; i<10; i++ ); // loop");
		assertEquals( 5, line.size() );
		//CodePart p = new CodePart( CodePart.KEYWORD, "for" );
		assertEquals( CodePart.KEYWORD, line.get(0).getDataType() );
		assertEquals( "for", line.get(0).getContent() );
		//assertEquals( "for", line.get(0).getContent() );
		//assertTrue( p.equals( line.get(0) ));
	}
}
