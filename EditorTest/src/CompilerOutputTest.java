import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CompilerOutputTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_grab_output_of_gcc_warning() {
		CompilerOutput compilerOutput = new CompilerOutput();
		assertArrayEquals( new String[] { "test.c", "3", "warning","unused variable 'i'" }, compilerOutput.parseLine( "test.c:3: warning: unused variable 'i'\n" ) );
	}

	@Test
	public void test_grab_output_of_gcc_error() {
		CompilerOutput compilerOutput = new CompilerOutput();
		assertArrayEquals( new String[] { "test.c", "3", "error","'asdfsd' undeclared (first use in this function)" }, compilerOutput.parseLine( "test.c:3: error: 'asdfsd' undeclared (first use in this function)\n" ) );
	}
}
