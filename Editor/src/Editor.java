import java.awt.EventQueue;
import javax.swing.JFrame;

import UI.DocumentView;

@SuppressWarnings("serial")
public class Editor extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocumentView frame = new DocumentView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
