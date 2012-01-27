package UI;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JTextPane;


@SuppressWarnings("serial")
public class JCodeTextArea extends JTextPane {
	public JCodeTextArea() {
		setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		setCaret( new CodeCarret() );
	}
}
