package restuarantPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class DrawRoundTable extends JComponent implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	private Table t;
	private Vector<Color> colors = new Vector<Color>();
	
	//custom exception: more people than chairs
	private class NoMoreChairsException extends Exception {
		private static final long serialVersionUID = 1L;

		public NoMoreChairsException(String msg) {
			super(msg);
		}
	}
	
	public DrawRoundTable(Table t) {
		this.t = t;
		
		//default: all full chairs
		for(int i = 0; i < t.getNumseats(); i++) {
			colors.add(Color.RED);
		}
		//so I don't call it more than once
		addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(t.isStatus() == true) {
			g.setColor(Color.GREEN);
			g.fillOval(5, 5, t.getSize().width, t.getSize().width);
			g.setColor(Color.BLACK);
			//to properly center 2 digit numbers
			if(t.getNumber() > 9) {
				g.drawString(Integer.toString(t.getNumber()), t.getSize().width/2 - 4, t.getSize().width/2 + 8);
			} else {
				g.drawString(Integer.toString(t.getNumber()), t.getSize().width/2 + 2, t.getSize().width/2 + 8);
			}
			
			//add chairs
			int counter = 1;
			for (int i = 0; i < t.getNumseats(); i++)
			{
				if(i % 4 == 0) {
					g.fillRect(0, 0, 10, 10);
					
					if(counter > 1) {
						g.fillRect(t.getSize().width, t.getSize().width / counter, 10, 10);
					}
				}
				if(i % 4 == 1) {
					g.fillRect(t.getSize().width / counter, t.getSize().width, 10, 10);
				}
				if(i % 4 == 2) {
					g.fillRect(0, t.getSize().width / counter, 10, 10);
				}
				if(i % 4 == 3) {
					g.fillRect(t.getSize().width / counter, 0, 10, 10);
					counter++;
				}
			}
		}
		if(t.isStatus() == false) {
			g.setColor(Color.RED);
			g.fillOval(5, 5, t.getSize().width, t.getSize().width);
			g.setColor(Color.BLACK);
			if(t.getNumber() > 9) {
				g.drawString(Integer.toString(t.getNumber()), t.getSize().width/2 - 4, t.getSize().width/2 + 8);
			} else {
				g.drawString(Integer.toString(t.getNumber()), t.getSize().width/2 + 2, t.getSize().width/2 + 8);
			}
			
			//add chairs
			int counter = 1;
			for (int i = 0; i < t.getNumseats(); i++)
			{
				if(i % 4 == 0) {
					g.setColor(colors.get(i));
					g.fillRect(0, 0, 10, 10);
					
					if(counter > 1) {
						g.fillRect(t.getSize().width, t.getSize().width / counter, 10, 10);
					}
				}
				if(i % 4 == 1) {
					g.setColor(colors.get(i));
					g.fillRect(t.getSize().width / counter, t.getSize().width, 10, 10);
				}
				if(i % 4 == 2) {
					g.setColor(colors.get(i));
					g.fillRect(0, t.getSize().width / counter, 10, 10);
				}
				if(i % 4 == 3) {
					g.setColor(colors.get(i));
					g.fillRect(t.getSize().width / counter, 0, 10, 10);
					counter++;
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(t.isStatus() == true) {
			//input validation
			try {
				//prompt user for number of people
				String input = JOptionPane.showInputDialog(this.getParent(), "How many people will be seated at this table?", "Input Dialog", JOptionPane.QUESTION_MESSAGE);
				int input2 = Integer.parseInt(input);
				
				if(input2 <= t.getNumseats()) {
					//change colors in vector to user input
					for(int i = 0; i < colors.size(); i++) {
						colors.set(i, Color.BLACK);
					}
					for(int i = 0; i < input2; i++) {
						colors.set(i, Color.RED);
					}
					//add changes and update
					t.setStatus(false);
					repaint();
				} 
				else {
					throw new NoMoreChairsException("Cannot accommodate this many people.");
				}
			} 
			catch(NumberFormatException nfe) {
				//if the user clicks cancel
				if(nfe.getMessage() != "null") {
					JOptionPane.showMessageDialog(this.getParent(), "Formatting Error: " + nfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			} 
			catch(NoMoreChairsException nmce) {
				JOptionPane.showMessageDialog(this.getParent(), nmce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(t.isStatus() == false) {
			t.setStatus(true);
			repaint();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}