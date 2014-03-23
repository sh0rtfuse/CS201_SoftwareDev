package restuarantPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class DrawRectTable extends JComponent implements MouseListener {
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
	
	public DrawRectTable(Table t) {
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
			g.fillRect(13, 13, t.getSize().width, t.getSize().height);
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(t.getNumber()), t.getSize().width/2 + 10, t.getSize().height/2 + 18);
			
			//add chairs
			int counter = 1;
			//chair layout A
			if(t.getSize().width > t.getSize().height) {
				for (int i = 0; i < t.getNumseats(); i++)
				{
					if(i % 6 == 0) {
						g.fillRect(0, (t.getSize().height/2 + 8), 10, 10);
						
						if(counter == 2) {
							g.fillRect((t.getSize().width/4 * counter) + 8, 0, 10, 10);
						}
					}
					if(i % 6 == 1) {
						g.fillRect((t.getSize().width + 15), (t.getSize().height/2 + 8), 10, 10);
						
						if(counter == 2) {
							g.fillRect((t.getSize().width/4 * counter) + 8, (t.getSize().height + 15), 10, 10);
						}
					}
					if(i % 6 == 2) {
						g.fillRect((t.getSize().width/4) + 8, 0, 10, 10);
						
						if(counter > 1) {
							g.fillRect((t.getSize().width/4 * 4) + 8, 0, 10, 10);
						}
					}
					if(i % 6 == 3) {
						g.fillRect((t.getSize().width/4) + 8, (t.getSize().height + 15), 10, 10);
						
						if(counter > 1) {
							g.fillRect((t.getSize().width/4 * 4) + 8, (t.getSize().height + 15), 10, 10);
						}
					}
					if(i % 6 == 4) {
						g.fillRect((t.getSize().width/4 * 3) + 8, 0, 10, 10);
						
						if(counter == 2) {
							g.fillRect((t.getSize().width/4 * 0) + 8, 0, 10, 10);
						}
					}
					if(i % 6 == 5) {
						g.fillRect((t.getSize().width/4 * 3) + 8, (t.getSize().height + 15), 10, 10);
						
						if(counter == 2) {
							g.fillRect((t.getSize().width/4 * 0) + 8, (t.getSize().height + 15), 10, 10);
						}
						counter++;
					}
				}	
			} 
			//chair layout B
			else if (t.getSize().width < t.getSize().height) {
				for (int i = 0; i < t.getNumseats(); i++)
				{
					if(i % 6 == 0) {
						g.fillRect((t.getSize().width/2) + 8, 0, 10, 10);
						
						if(counter > 1) {
							g.fillRect(0, (t.getSize().height/4 * counter) + 8, 10, 10);
						}
					}
					if(i % 6 == 1) {
						g.fillRect((t.getSize().width/2) + 8, (t.getSize().height + 15), 10, 10);
						
						if(counter > 1) {
							g.fillRect((t.getSize().width + 15), (t.getSize().height/4 * counter) + 8, 10, 10);
						}
					}
					if(i % 6 == 2) {
						g.fillRect(0, (t.getSize().height/4) + 8, 10, 10);
						
						if(counter > 1) {
							g.fillRect(0, (t.getSize().height/4 * 0) + 8, 10, 10);
						}
					}
					if(i % 6 == 3) {
						g.fillRect((t.getSize().width + 15), (t.getSize().height/4) + 8, 10, 10);
						
						if(counter > 1) {
							g.fillRect((t.getSize().width + 15), (t.getSize().height/4 * 0) + 8, 10, 10);
						}
					}
					if(i % 6 == 4) {
						g.fillRect(0, (t.getSize().height/4 * 3) + 8, 10, 10);
						
						if(counter > 1) {
							g.fillRect(0, (t.getSize().height/4 * 4) + 8, 10, 10);
						}
					}
					if(i % 6 == 5) {
						g.fillRect((t.getSize().width + 15), (t.getSize().height/4 * 3) + 8, 10, 10);
						
						if(counter > 1) {
							g.fillRect((t.getSize().width + 15), (t.getSize().height/4 * 4) + 8, 10, 10);
						}
						counter++;
					}
				}
			}
		}
		if(t.isStatus() == false) {
			g.setColor(Color.RED);
			g.fillRect(13, 13, t.getSize().width, t.getSize().height);
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(t.getNumber()), t.getSize().width/2 + 10, t.getSize().height/2 + 18);
			
			//add chairs
			int counter = 1;
			//chair layout A
			if(t.getSize().width > t.getSize().height) {
				for (int i = 0; i < t.getNumseats(); i++)
				{
					if(i % 6 == 0) {
						g.setColor(colors.get(i));
						g.fillRect(0, (t.getSize().height/2 + 8), 10, 10);
						
						if(counter == 2) {
							g.fillRect((t.getSize().width/4 * counter) + 8, 0, 10, 10);
						}
					}
					if(i % 6 == 1) {
						g.setColor(colors.get(i));
						g.fillRect((t.getSize().width + 15), (t.getSize().height/2 + 8), 10, 10);
						
						if(counter == 2) {
							g.fillRect((t.getSize().width/4 * counter) + 8, (t.getSize().height + 15), 10, 10);
						}
					}
					if(i % 6 == 2) {
						g.setColor(colors.get(i));
						g.fillRect((t.getSize().width/4) + 8, 0, 10, 10);
						
						if(counter > 1) {
							g.fillRect((t.getSize().width/4 * 4) + 8, 0, 10, 10);
						}
					}
					if(i % 6 == 3) {
						g.setColor(colors.get(i));
						g.fillRect((t.getSize().width/4) + 8, (t.getSize().height + 15), 10, 10);
						
						if(counter > 1) {
							g.fillRect((t.getSize().width/4 * 4) + 8, (t.getSize().height + 15), 10, 10);
						}
					}
					if(i % 6 == 4) {
						g.setColor(colors.get(i));
						g.fillRect((t.getSize().width/4 * 3) + 8, 0, 10, 10);
						
						if(counter == 2) {
							g.fillRect((t.getSize().width/4 * 0) + 8, 0, 10, 10);
						}
					}
					if(i % 6 == 5) {
						g.setColor(colors.get(i));
						g.fillRect((t.getSize().width/4 * 3) + 8, (t.getSize().height + 15), 10, 10);
						
						if(counter == 2) {
							g.fillRect((t.getSize().width/4 * 0) + 8, (t.getSize().height + 15), 10, 10);
						}
						counter++;
					}
				}	
			} 
			//chair layout B
			else if (t.getSize().width < t.getSize().height) {
				for (int i = 0; i < t.getNumseats(); i++)
				{
					if(i % 6 == 0) {
						g.setColor(colors.get(i));
						g.fillRect((t.getSize().width/2) + 8, 0, 10, 10);
						
						if(counter > 1) {
							g.fillRect(0, (t.getSize().height/4 * counter) + 8, 10, 10);
						}
					}
					if(i % 6 == 1) {
						g.setColor(colors.get(i));
						g.fillRect((t.getSize().width/2) + 8, (t.getSize().height + 15), 10, 10);
						
						if(counter > 1) {
							g.fillRect((t.getSize().width + 15), (t.getSize().height/4 * counter) + 8, 10, 10);
						}
					}
					if(i % 6 == 2) {
						g.setColor(colors.get(i));
						g.fillRect(0, (t.getSize().height/4) + 8, 10, 10);
						
						if(counter > 1) {
							g.fillRect(0, (t.getSize().height/4 * 0) + 8, 10, 10);
						}
					}
					if(i % 6 == 3) {
						g.setColor(colors.get(i));
						g.fillRect((t.getSize().width + 15), (t.getSize().height/4) + 8, 10, 10);
						
						if(counter > 1) {
							g.fillRect((t.getSize().width + 15), (t.getSize().height/4 * 0) + 8, 10, 10);
						}
					}
					if(i % 6 == 4) {
						g.setColor(colors.get(i));
						g.fillRect(0, (t.getSize().height/4 * 3) + 8, 10, 10);
						
						if(counter > 1) {
							g.fillRect(0, (t.getSize().height/4 * 4) + 8, 10, 10);
						}
					}
					if(i % 6 == 5) {
						g.setColor(colors.get(i));
						g.fillRect((t.getSize().width + 15), (t.getSize().height/4 * 3) + 8, 10, 10);
						
						if(counter > 1) {
							g.fillRect((t.getSize().width + 15), (t.getSize().height/4 * 4) + 8, 10, 10);
						}
						counter++;
					}
				}
			}
		}
	}
	
	//used to update table
	public Table getT() {
		return t;
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