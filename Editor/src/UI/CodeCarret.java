package UI;
import javax.swing.text.DefaultCaret;

@SuppressWarnings("serial")
public class CodeCarret extends DefaultCaret {

	public CodeCarret() {
		setBlinkRate(200);
	}
/*
	@Override
	public void paint(Graphics g) {
		JTextComponent comp = getComponent();
		if (comp == null)
			return;

		int dot = getDot();
		Rectangle r = null;
		try {
			r = comp.modelToView(dot);
		} catch (BadLocationException e) {
			return;
		}
		if (r == null)
			return;

		int dist = r.height * 4 / 5 - 3; // will be distance from r.y to top

		if ((x != r.x) || (y != r.y + dist)) {
			// paint() has been called directly, without a previous call to
			// damage(), so do some cleanup. (This happens, for example, when
			// the
			// text component is resized.)
			repaint(); // erase previous location of caret
			x = r.x; // set new values for x,y,width,height
			y = r.y + dist;
			width = 15;
			height = 10;
		}

		if (isVisible())
			g.setColor(comp.getCaretColor());
		else
			g.setColor(comp.getBackground());

		g.fillRect(r.x, r.y + dist, width, height); // 5 horizontal px
	}*/
}
