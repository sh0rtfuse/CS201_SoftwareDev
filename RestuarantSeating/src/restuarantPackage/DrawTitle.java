package restuarantPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

public class DrawTitle extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private Title t;
	
	public DrawTitle(Title t) {
		this.t = t;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString(t.getName(), 1, 25);
	}
}