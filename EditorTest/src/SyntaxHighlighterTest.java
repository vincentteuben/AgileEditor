import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SyntaxHighlighter.LanguageC;
import SyntaxHighlighter.SyntaxHighlightedLine;
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
		ArrayList<SyntaxHighlightedLine> line = syntax.highlightline("this is a test");
		assertEquals( 1, line.size() );
		assertEquals( SyntaxHighlightedLine.TEXT, line.get(0).getText() );
		assertEquals( "this is a test", line.get(0).getContent() );		
	}

	@Test
	public void test_cpp_style_comment_highlighting() {
		ArrayList<SyntaxHighlightedLine> line = syntax.highlightline("// This is a comment");
		assertEquals( 1, line.size() );
		assertEquals( SyntaxHighlightedLine.COMMENT, line.get(0).getText() );
		assertEquals( "// This is a comment", line.get(0).getContent() );		
	}
	
	@Test
	public void test_cpp_style_comment_after_code_highlighting() {
		ArrayList<SyntaxHighlightedLine> line = syntax.highlightline("asdf //foo");
		assertEquals( 2, line.size() );
		assertEquals( SyntaxHighlightedLine.TEXT, line.get(0).getText() );
		assertEquals( "asdf ", line.get(0).getContent() );		
		assertEquals( SyntaxHighlightedLine.COMMENT, line.get(1).getText() );
		assertEquals( "//foo", line.get(1).getContent() );		
	}
	
	// TODO: I was here...
	@Test
	public void test_c_style_comment_highlighting() {
		assertEquals( "<comment>/* test */</comment>", syntax.highlight("/* test */"));
	}
	
	@Test
	public void test_c_style_comment_in_line_highlighting() {
		assertEquals( "a = <comment>/*10*/</comment>11;", syntax.highlight("a = /*10*/11;"));		
	}
	
	@Test
	public void test_get_a_type_highlighting() {
		assertEquals( "<type>int</type> i;", syntax.highlight("int i;"));
		assertEquals( "<type>char</type> c;", syntax.highlight("char c;"));
		assertEquals( "<type>short</type> s;", syntax.highlight("short s;"));
	}
}
