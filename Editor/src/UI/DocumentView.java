package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


@SuppressWarnings("serial")
public class DocumentView extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public DocumentView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTextPane textArea = new JCodeTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
		
		StyledDocument doc = createCodeDocument();
		textArea.setDocument(doc);
	}

	private StyledDocument createCodeDocument() {
	    StyleContext sc = new StyleContext();
	    StyledDocument doc = new DefaultStyledDocument(sc);
		
		Style defaultStyle = sc.getStyle(StyleContext.DEFAULT_STYLE);
		doc.addStyle("DefaultStyle", null);
		Style codeStyle = sc.addStyle("CodeStyle", defaultStyle);
	    StyleConstants.setLeftIndent(codeStyle, 16);
	    StyleConstants.setRightIndent(codeStyle, 16);
	    StyleConstants.setFirstLineIndent(codeStyle, 16);
	    StyleConstants.setFontFamily(codeStyle, "arial");
	    StyleConstants.setFontSize(codeStyle, 14);	    
	    StyleConstants.setForeground(codeStyle, Color.GRAY);
	    doc.addStyle("CodeStyle", null);
	    
	    Style dataTypeStyle = sc.addStyle("DataTypeStyle", defaultStyle);
	    StyleConstants.setLeftIndent(codeStyle, 16);
	    StyleConstants.setRightIndent(codeStyle, 16);
	    StyleConstants.setFirstLineIndent(codeStyle, 16);
	    StyleConstants.setFontFamily(codeStyle, "arial");
	    StyleConstants.setFontSize(codeStyle, 14);	    
	    StyleConstants.setForeground(codeStyle, Color.BLUE);
	    doc.addStyle("DataTypeStyle", codeStyle);

		try {
			doc.insertString(doc.getLength(), "normal line\n", codeStyle);
			doc.insertString(doc.getLength(), "int", dataTypeStyle);
			doc.insertString(doc.getLength(), " i=0\n", codeStyle);
			
		} catch (BadLocationException e) {}

	    return doc;
	}

}
