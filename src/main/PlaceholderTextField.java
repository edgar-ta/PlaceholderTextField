package main;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 * Simple class that displays a placeholder on the text field 
 * @author Edgar Trejo Avila
 *
 */
@SuppressWarnings("serial")
public class PlaceholderTextField extends JTextField {
	String placeholder;

	/**
	 * Creates the text field
	 * @param doc Document of the field
	 * @param text Initial text of the field
	 * @param placeholder Placeholder of the field
	 */
	public PlaceholderTextField(Document doc, String text, String placeholder) {
		super(doc, text, 0);
		this.placeholder = placeholder;
	}

	/**
	 * Creates the text field
	 * <hr>
	 * Calls {@link #PlaceholderTextField(Document, String, String)}
	 * with {@code doc = null}
	 * @param text Initial text of the field
	 * @param placeholder Placeholder of the field
	 */
	public PlaceholderTextField(String text, String placeholder) {
		this(null, text, placeholder);
	}
	
	/**
	 * Creates the text field
	 * <hr>
	 * Calls {@link #PlaceholderTextField(String, String)}
	 * with {@code text = null}
	 * @param placeholder Placeholder of the field
	 */
	public PlaceholderTextField(String placeholder) {
		this(null, placeholder);
	}
	
	/**
	 * Creates the text field
	 * <hr>
	 * Calls {@link #PlaceholderTextField(String)}
	 * with {@code placeholder = null}
	 */
	public PlaceholderTextField() {
		this(null);
	}
	
	/**
	 * Paints the placeholder of the field; horizontally to the left,
	 * and vertically to the center.
	 * <hr>
	 * I couldn't find any explanaition other than the max ascent of
	 * the metrics of a font is a negative value. If: <br>
	 * 
	 * {@code h = } the height of the field, <br>
	 * {@code a = } the max ascent, <br>
	 * {@code d = } the max descent, <br>
	 * since {@code a} is negative,  <br>
	 * {@code c = d - a} is the sum of the absolute values of the ascent and
	 * descent, which is of course the font's case whole height ({@code c = |d| + |a|}) <br>
	 * 
	 * Then,
	 * {@code y = (h - c) / 2} is just the coordinate to center the case of the font within
	 * the height of the text field
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (!getText().equals("") || placeholder == null || placeholder.equals("")) return;
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(getDisabledTextColor());
		g2d.setFont(getFont());
		
		FontMetrics metrics = g2d.getFontMetrics();
		float y = (getHeight() + metrics.getMaxAscent() - metrics.getMaxDescent()) / 2;
		g2d.drawString(placeholder, getInsets().left, y);
	}
	
	/**
	 * Getter
	 * @return Placeholder
	 */
	public String getPlaceholder() {
		return placeholder;
	}
	
	/**
	 * Setter
	 * <hr>
	 * Sets the value and repaints the text field
	 * to show the changes
	 * @param placeholder New placeholder
	 */
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		repaint();
	}
}
