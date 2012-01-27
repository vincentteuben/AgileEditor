import java.util.ArrayList;
import java.util.StringTokenizer;


public class CompilerOutput {

	public String[] parseLine(String line) {
		ArrayList<String> tokens = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(line, ":");
		while( st.hasMoreTokens() )
			tokens.add( st.nextToken().trim() );

		String[] result = new String[tokens.size()];
		tokens.toArray(result);
		return result;
	}
}
