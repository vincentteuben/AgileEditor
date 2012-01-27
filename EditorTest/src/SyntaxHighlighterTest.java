import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		assertEquals( "this is a test", syntax.highlight("this is a test"));
	}

	@Test
	public void test_cpp_style_comment_highlighting() {
		assertEquals( "<comment>// This is a comment</comment>", syntax.highlight("// This is a comment"));
	}
	
	@Test
	public void test_cpp_style_comment_after_code_highlighting() {
		assertEquals( "asdf <comment>//foo</comment>", syntax.highlight("asdf //foo"));
	}
	
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
