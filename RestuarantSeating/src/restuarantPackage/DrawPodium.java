package restuarantPackage;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class DrawPodium extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private Podium p;
	
	public DrawPodium(Podium p) {
		this.p = p;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, p.getSize().width, p.getSize().height);
		g.setColor(Color.BLACK);
		g.drawString("P", p.getSize().width/2 - 4, p.getSize().height/2 + 4);
	}
}