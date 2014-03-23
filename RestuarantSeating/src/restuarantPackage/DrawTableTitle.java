package restuarantPackage;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class DrawTableTitle extends JComponent {
	private static final long serialVersionUID = 1L;
		
	private Column c;
		
	public DrawTableTitle(Column c) {
		this.c = c;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.setFont(c.getF());
		g.drawString(c.getName(), 1, 20);
	}
}