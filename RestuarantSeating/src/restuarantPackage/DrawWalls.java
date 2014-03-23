package restuarantPackage;

import java.awt.Graphics;

import javax.swing.JComponent;

public class DrawWalls extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private Wall w;
	
	public DrawWalls(Wall w) {
		this.w = w;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(w.getStartLocation().x == w.getEndLocation().x) {
			g.drawLine(1, 1, 1, (int) w.getStartLocation().distance(w.getEndLocation()));
		}
		else if(w.getStartLocation().y == w.getEndLocation().y) {
			g.drawLine(1, 1, (int) w.getStartLocation().distance(w.getEndLocation()), 1);
		}
	}
}