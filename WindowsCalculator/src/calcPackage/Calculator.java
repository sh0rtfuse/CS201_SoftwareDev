//Raymond Dam USCID:3325417660

package calcPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Calculator extends JFrame {
	private static final long serialVersionUID = 1L;

	private boolean radians = false;

	public Calculator() {
		super("Calculator");
		
		try {
			setSize(500, 324);
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 23);
			Font f2 = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
			
			//Menu
			JMenuBar jmb = new JMenuBar();
			jmb.setBackground(new Color(245, 246, 247));
			jmb.setBorder(BorderFactory.createEmptyBorder());
			JMenu edit = new JMenu("Edit");
			edit.setMnemonic(KeyEvent.VK_E);
			
			JMenu help = new JMenu("Help");
			help.setMnemonic(KeyEvent.VK_H);
			
			JMenuItem copy = new JMenuItem("Copy");
			copy.setActionCommand((String)TransferHandler.getCopyAction().getValue(Action.NAME));
			copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
			copy.setMnemonic(KeyEvent.VK_C);
			
			JMenuItem paste = new JMenuItem("Paste");
			paste.setActionCommand((String)TransferHandler.getCopyAction().getValue(Action.NAME));
			paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
			paste.setMnemonic(KeyEvent.VK_P);
			
			JMenuItem about = new JMenuItem("About Calculator");
			about.addActionListener(new aboutMenu());
			
			edit.add(copy);
			edit.add(paste);
			help.add(about);
			jmb.add(edit);
			jmb.add(help);
			
			setJMenuBar(jmb);
			
			//text fields
			JPanel display = new JPanel();
			display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));
			display.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 5, 10), new LineBorder(Color.lightGray, 1, true)));
			display.setBackground(new Color(217, 228, 241));
			
			JTextField readout = new JTextField("", 20);
			readout.setEnabled(false);
			//Configure the text field not to grey out the entry 
	        Color fgColor = UIManager.getColor("TextField.foreground");  
	        readout.setDisabledTextColor(fgColor);
	        readout.setHighlighter(null);
	        readout.setDragEnabled(false);
	        //Create a seamless field better both text fields
	        readout.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        readout.setHorizontalAlignment(JTextField.RIGHT);
	        readout.setFont(f2);
	        readout.setBackground(new Color(217, 228, 241));
	        
	        JTextField entry = new JTextField("", 20);
	        entry.setEditable(false);
	        Color fgColor2 = UIManager.getColor("TextField.foreground");  
	        entry.setDisabledTextColor(fgColor2);
	        entry.setHighlighter(null);
	        entry.setDragEnabled(false);
	        entry.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        entry.setHorizontalAlignment(JTextField.RIGHT);
	        entry.setFont(f);  
	        entry.setBackground(new Color(217, 228, 241));
	        entry.setText("0");
			
	        display.add(readout);
	        display.add(entry);
			add(display,BorderLayout.NORTH);
			
			//Buttons
			JPanel keypad = new JPanel();
			keypad.setLayout(new GridBagLayout());
			keypad.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
			keypad.setBackground(new Color(217, 228, 241));
			GridBagConstraints gbc = new GridBagConstraints();
			
			JRadioButton deg = new JRadioButton("Degrees");
			JRadioButton rad = new JRadioButton("Radians");
			//Box to store both buttons
			Box rButtons = Box.createHorizontalBox();
			rButtons.add(deg);
			rButtons.add(rad);
			rButtons.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1, true));
			//default select to degrees
			deg.setSelected(true);
			
			//deselect one button when the other is selected
			ButtonGroup bg = new ButtonGroup();
			bg.add(deg);
			bg.add(rad);
			
			//add radio buttons to the panel
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.weightx = 1;
			gbc.weighty = 1;
	
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.ipadx = 120;
			gbc.ipady = 10;
			gbc.gridwidth = 4;
			keypad.add(rButtons,gbc);
			gbc.gridwidth = 1;			
			
			//column 1
			//moved lnv button to end
			
			JButton sinh = new JButton("sinh");
			sinh.addActionListener(new modiferButtons(readout, entry, "sinh"));
			gbc.gridx = 0;
			gbc.gridy = 2;
			keypad.add(sinh, gbc);
			
			JButton cosh = new JButton("cosh");
			cosh.addActionListener(new modiferButtons(readout, entry, "cosh"));
			gbc.gridx = 0;
			gbc.gridy = 3;
			keypad.add(cosh, gbc);
			
			JButton tanh = new JButton("tanh");
			cosh.addActionListener(new modiferButtons(readout, entry, "tanh"));
			gbc.gridx = 0;
			gbc.gridy = 4;
			keypad.add(tanh, gbc);
			
			JButton pi = new JButton("\u03c0");
			pi.addActionListener(new piButton(entry));
			gbc.gridx = 0;
			gbc.gridy = 5;
			keypad.add(pi, gbc);
			
			//column 2
			JButton ln = new JButton("ln");
			ln.addActionListener(new modiferButtons(readout, entry, "ln"));
			gbc.gridx = 1;
			gbc.gridy = 1;
			keypad.add(ln, gbc);
			
			JButton sin = new JButton("sin");
			sin.addActionListener(new modiferButtons(readout, entry, "sin"));
			gbc.gridx = 1;
			gbc.gridy = 2;
			keypad.add(sin, gbc);
			
			JButton cos = new JButton("cos");
			cos.addActionListener(new modiferButtons(readout, entry, "cos"));
			gbc.gridx = 1;
			gbc.gridy = 3;
			keypad.add(cos, gbc);
			
			JButton tan = new JButton("tan");
			tan.addActionListener(new modiferButtons(readout, entry, "tan"));
			gbc.gridx = 1;
			gbc.gridy = 4;
			keypad.add(tan, gbc);
			
			JButton mod = new JButton("Mod");
			mod.addActionListener(new modButton(readout, entry));
			gbc.gridx = 1;
			gbc.gridy = 5;
			keypad.add(mod, gbc);
			
			//column 3
			JButton leftP = new JButton("(");
			gbc.gridx = 2;
			gbc.gridy = 1;
			keypad.add(leftP, gbc);
			
			JButton xsquared = new JButton("x" + "\u00B2");
			xsquared.addActionListener(new modiferButtons(readout, entry, "sqr"));
			gbc.gridx = 2;
			gbc.gridy = 2;
			keypad.add(xsquared, gbc);
			
			JButton xy = new JButton("x" + "\u02B8");
			xy.addActionListener(new opsButtons(readout, entry, "^"));
			gbc.gridx = 2;
			gbc.gridy = 3;
			keypad.add(xy, gbc);
			
			JButton xcubed = new JButton("x" + "\u00B3");
			xcubed.addActionListener(new modiferButtons(readout, entry, "cube"));
			gbc.gridx = 2;
			gbc.gridy = 4;
			keypad.add(xcubed, gbc);
			
			JButton log = new JButton("log");
			log.addActionListener(new modiferButtons(readout, entry, "log"));
			gbc.gridx = 2;
			gbc.gridy = 5;
			keypad.add(log, gbc);
			
			//column 4
			JButton rightP = new JButton(")");
			gbc.gridx = 3;
			gbc.gridy = 1;
			keypad.add(rightP, gbc);
			
			JButton factorial = new JButton("n!");
			factorial.addActionListener(new modiferButtons(readout, entry, "fact"));
			gbc.gridx = 3;
			gbc.gridy = 2;
			keypad.add(factorial, gbc);
			
			JButton yroot = new JButton("\u02B8" + "\u221A" + "x");
			yroot.addActionListener(new yRootButton(readout, entry));
			gbc.gridx = 3;
			gbc.gridy = 3;
			keypad.add(yroot, gbc);
			
			JButton cubedrt = new JButton("\u221B" + "x");
			cubedrt.addActionListener(new modiferButtons(readout, entry, "cuberoot"));
			gbc.gridx = 3;
			gbc.gridy = 4;
			keypad.add(cubedrt, gbc);
			
			JButton tenx = new JButton("10" + "\u02E3");
			tenx.addActionListener(new modiferButtons(readout, entry, "powten"));
			gbc.gridx = 3;
			gbc.gridy = 5;
			keypad.add(tenx, gbc);
			
			//column 5
			JButton arrow = new JButton("\u2190");
			arrow.addActionListener(new delete(entry));
			gbc.gridx = 4;
			gbc.gridy = 1;
			keypad.add(arrow, gbc);
			
			JButton seven = new JButton("7");
			seven.addActionListener(new numPressed(entry, "7"));
			gbc.gridx = 4;
			gbc.gridy = 2;
			keypad.add(seven, gbc);
			
			JButton four = new JButton("4");
			four.addActionListener(new numPressed(entry, "4"));
			gbc.gridx = 4;
			gbc.gridy = 3;
			keypad.add(four, gbc);
			
			JButton one = new JButton("1");
			one.addActionListener(new numPressed(entry, "1"));
			gbc.gridx = 4;
			gbc.gridy = 4;
			keypad.add(one, gbc);
			
			JButton zero = new JButton("0");
			zero.addActionListener(new numPressed(entry, "0"));
			gbc.gridx = 4;
			gbc.gridy = 5;
			gbc.gridwidth = 2;
			keypad.add(zero, gbc);
			
			//column 6
			gbc.gridwidth = 1;
			JButton CE = new JButton("CE");
			CE.addActionListener(new clearEntry(entry));
			gbc.gridx = 5;
			gbc.gridy = 1;
			keypad.add(CE, gbc);
			
			JButton eight = new JButton("8");
			eight.addActionListener(new numPressed(entry, "8"));
			gbc.gridx = 5;
			gbc.gridy = 2;
			keypad.add(eight, gbc);
			
			JButton five = new JButton("5");
			five.addActionListener(new numPressed(entry, "5"));
			gbc.gridx = 5;
			gbc.gridy = 3;
			keypad.add(five, gbc);
			
			JButton two = new JButton("2");
			two.addActionListener(new numPressed(entry, "2"));
			gbc.gridx = 5;
			gbc.gridy = 4;
			keypad.add(two, gbc);
			
			//column 7
			JButton C = new JButton("C");
			C.addActionListener(new clear(readout, entry));
			gbc.gridx = 6;
			gbc.gridy = 1;
			keypad.add(C, gbc);
			
			JButton nine = new JButton("9");
			nine.addActionListener(new numPressed(entry, "9"));
			gbc.gridx = 6;
			gbc.gridy = 2;
			keypad.add(nine, gbc);
			
			JButton six = new JButton("6");
			six.addActionListener(new numPressed(entry, "6"));
			gbc.gridx = 6;
			gbc.gridy = 3;
			keypad.add(six, gbc);
			
			JButton three = new JButton("3");
			three.addActionListener(new numPressed(entry, "3"));
			gbc.gridx = 6;
			gbc.gridy = 4;
			keypad.add(three, gbc);
			
			JButton decimal = new JButton(".");
			decimal.addActionListener(new decimalButton(entry));
			gbc.gridx = 6;
			gbc.gridy = 5;
			keypad.add(decimal, gbc);
			
			//column 8
			JButton plusMinus = new JButton("\u00B1");
			plusMinus.addActionListener(new plusOrMinus(entry));
			gbc.gridx = 7;
			gbc.gridy = 1;
			keypad.add(plusMinus, gbc);
			
			JButton divide = new JButton("/");
			divide.addActionListener(new opsButtons(readout, entry, "/"));
			gbc.gridx = 7;
			gbc.gridy = 2;
			keypad.add(divide, gbc);
			
			JButton multiply = new JButton("*");
			multiply.addActionListener(new opsButtons(readout, entry, "*"));
			gbc.gridx = 7;
			gbc.gridy = 3;
			keypad.add(multiply, gbc);
			
			JButton minus = new JButton("-");
			minus.addActionListener(new opsButtons(readout, entry, "-"));
			gbc.gridx = 7;
			gbc.gridy = 4;
			keypad.add(minus, gbc);
			
			JButton plus = new JButton("+");
			plus.addActionListener(new opsButtons(readout, entry, "+"));
			gbc.gridx = 7;
			gbc.gridy = 5;
			keypad.add(plus, gbc);
			
			//column 9
			JButton sqrt = new JButton("\u221A");
			sqrt.addActionListener(new modiferButtons(readout, entry, "sqrt"));
			gbc.gridx = 8;
			gbc.gridy = 1;
			keypad.add(sqrt, gbc);
			
			JButton percent = new JButton("%");
			percent.setEnabled(false);
			gbc.gridx = 8;
			gbc.gridy = 2;
			keypad.add(percent, gbc);
			
			JButton recip = new JButton("1/x");
			recip.addActionListener(new modiferButtons(readout, entry, "reciproc"));
			gbc.gridx = 8;
			gbc.gridy = 3;
			keypad.add(recip, gbc);
			
			JButton equals = new JButton("=");
			equals.addActionListener(new enterButton(readout, entry));
			gbc.gridx = 8;
			gbc.gridy = 4;
			gbc.fill = GridBagConstraints.VERTICAL;
			gbc.gridheight = 2;
			keypad.add(equals, gbc);
			
			//array to store inverse buttons for manipulation
			JButton jbArray [] = new JButton [] {sinh, cosh, tanh, pi, ln, sin, cos, tan};
			
			//moved here from column 1
			JToggleButton inverseButton = new JToggleButton("lnv");
			inverseButton.addItemListener(new InverseListener(jbArray));
			gbc.gridheight = 1;
			gbc.gridx = 0;
			gbc.gridy = 1;
			keypad.add(inverseButton, gbc);
			
			//keyboard functionality
			KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	        kfm.addKeyEventDispatcher(new Dispatcher(readout, entry));
	        
	        //listen to button changes
			rad.addItemListener(new radiansButton());
			deg.addItemListener(new degreesButton());
			
			add(keypad, BorderLayout.CENTER);
			
			setVisible(true);
		}
		catch (NullPointerException npe) {
			npe.getMessage();
		}
		catch (IllegalArgumentException iae) {
			iae.getMessage();
		}
	}
	
	//toggles between inverse and normal functionality
	private class InverseListener implements ItemListener {
		private JButton jb [] = new JButton [8];
		
		public InverseListener(JButton jb []) {
			for (int i = 0; i < jb.length; i++) {
				this.jb [i] = jb [i];
			}
		}

		public void itemStateChanged(ItemEvent ie) {
			Font small = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
			Font big = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
			if (ie.getStateChange() == ItemEvent.SELECTED) {
				for (int i = 0; i < jb.length; i++) {
					jb [i].setFont(small);	
				}
				//set each button to their inverse state
				jb [0].setText("sinh" + "\u207B" + "\u00B9");
				jb [1].setText("cosh" + "\u207B" + "\u00B9");
				jb [2].setText("tanh" + "\u207B" + "\u00B9");
				jb [3].setText("2*" + "\u03c0");
				jb [4].setFont(big);
				jb [4].setText("e" + "\u02E3");
				jb [5].setText("sin" + "\u207B" + "\u00B9");
				jb [6].setText("cos" + "\u207B" + "\u00B9");
				jb [7].setText("tan" + "\u207B" + "\u00B9");
		    } 
			else {
				for (int i = 0; i < jb.length; i++) {
					jb [i].setFont(big);	
				}
				//reset each button to their original states
				jb [0].setText("sinh");
				jb [1].setText("cosh");
				jb [2].setText("tanh");
				jb [3].setText("\u03c0");
				jb [4].setText("ln");
				jb [5].setText("sin");
				jb [6].setText("cos");
				jb [7].setText("tan");
		    }	
		}	
	}
	private class radiansButton implements ItemListener {
		public void itemStateChanged(ItemEvent ie) {
			if(ie.getStateChange() == ItemEvent.SELECTED) {
				radians = true;
			}
		}
	}
	private class degreesButton implements ItemListener {
		public void itemStateChanged(ItemEvent ie) {
			if(ie.getStateChange() == ItemEvent.SELECTED) {
				radians = false;
			}
		}
	}
	
	//the following actionlisteners correspond to JButton functionality when pressed
	private class numPressed implements ActionListener {
		private JTextField jtf;
		private String textInsert;
		
		public numPressed(JTextField jtf, String textInsert) {
			this.jtf = jtf;
			this.textInsert = textInsert;
		}
		
		public void actionPerformed(ActionEvent ae) {
			if(!jtf.getText().equals("0")) {
				jtf.setText(jtf.getText() + textInsert);
			} else {
				jtf.setText(textInsert);
			}
		}
	}
	private class clearEntry implements ActionListener {
		private JTextField jtf;
		
		public clearEntry(JTextField jtf) {
			this.jtf = jtf;
		}
		public void actionPerformed(ActionEvent ae) {
			jtf.setText("0");
		}
	}
	private class clear implements ActionListener {
		private JTextField jtf1, jtf2;
		
		public clear(JTextField readout, JTextField entry) {
			this.jtf1 = readout;
			this.jtf2 = entry;
		}
		public void actionPerformed(ActionEvent ae) {
			jtf1.setText("");
			jtf2.setText("0");
		}
	}
	private class delete implements ActionListener {
		private JTextField jtf;
		
		public delete(JTextField jtf) {
			this.jtf = jtf;
		}
		public void actionPerformed(ActionEvent ae) {
			if(!jtf.getText().equals("0")) {
				jtf.setText(jtf.getText().substring(0, jtf.getText().length() - 1));
			}
			if(jtf.getText().length() == 0) {
				jtf.setText("0");
			}
		}
	}
	private class plusOrMinus implements ActionListener {
		private JTextField jtf1;
		public plusOrMinus (JTextField entry) {
			this.jtf1 = entry;
		}
		public void actionPerformed(ActionEvent ae) {
			if(!jtf1.getText().equals("0")) {
				//checks last letter of the string
				if(jtf1.getText().startsWith("-")) {
					//if negative,change back to positve
					jtf1.setText(jtf1.getText().substring(1));
				} else {
					//if postive, make negative
					jtf1.setText("-" + jtf1.getText());
				}
			}
		}
	}
	//division, multiplication, subtraction, addition
	//addition, multiplcation, division
	private class opsButtons implements ActionListener {
		private JTextField jtf1, jtf2;
		private String operator;
		
		public opsButtons(JTextField readout, JTextField entry, String operator) {
			this.jtf1 = readout;
			this.jtf2 = entry;
			this.operator = operator;
		}
		public void actionPerformed(ActionEvent ae) {
			if(jtf2.getText().equals("0")) {
				if(!jtf1.getText().isEmpty()) {
					if(!((jtf1.getText().endsWith("/ ")) || (jtf1.getText().endsWith("* ")) || (jtf1.getText().endsWith("- ")) || (jtf1.getText().endsWith("+ ")) || (jtf1.getText().endsWith("^ ")))) {
						jtf1.setText(jtf1.getText() + " " + operator + " ");
					}
				} else {
					jtf1.setText(jtf2.getText() + " " + operator + " ");
				}
			} else {
				if(jtf1.getText().isEmpty()) {
					jtf1.setText(jtf2.getText() + " " + operator + " ");
				} else {
					if(!((jtf1.getText().endsWith("/ ")) || (jtf1.getText().endsWith("* ")) || (jtf1.getText().endsWith("- ")) || (jtf1.getText().endsWith("+ ")) || (jtf1.getText().endsWith("^ ")))) {
						jtf1.setText(jtf1.getText() + " " + operator + " ");
					}
				}
			}
		}
	}
	//sqrt, reciprical, powten, cubedroot, factorial, sqr, etc.
	//cos, sin, tan, sqrt, etc.
	private class modiferButtons implements ActionListener {
		private JTextField jtf1, jtf2;
		private String function;
		
		public modiferButtons(JTextField readout, JTextField entry, String function) {
			this.jtf1 = readout;
			this.jtf2 = entry;
			this.function = function;
		}
		public void actionPerformed(ActionEvent ae) {
			if(!jtf2.getText().equals("0")) {
				
				if((jtf1.getText().endsWith("/ ")) || (jtf1.getText().endsWith("* ")) || (jtf1.getText().endsWith("- ")) || (jtf1.getText().endsWith("+ "))) {
					
					jtf1.setText(jtf1.getText() + function + "(" + jtf2.getText() + ")");
				}
				else {
					jtf1.setText(function + "(" + jtf2.getText() + ")");
					
					double val = Double.parseDouble(jtf2.getText());
					
					if(function == "sin") {
						if(radians == false) {
							jtf2.setText(truncator(Math.sin(Math.toRadians(val))));
						} else {
							jtf2.setText(truncator(Math.sin(val)));
						}
					}
					if(function == "cos") {
						if(radians == false) {
							jtf2.setText(truncator(Math.cos(Math.toRadians(val))));
						} else {
							jtf2.setText(truncator(Math.cos(val)));
						}
					}
					if(function == "tan") {
						if(radians == false) {
							jtf2.setText(truncator(Math.tan(Math.toRadians(val))));
						} else {
							jtf2.setText(truncator(Math.tan(val)));
						}
					}
					if(function == "sinh") {
						if(radians == false) {
							jtf2.setText(truncator(Math.sinh(Math.toRadians(val))));
						} else {
							jtf2.setText(truncator(Math.sinh(val)));
						}
					}
					if(function == "cosh") {
						if(radians == false) {
							jtf2.setText(truncator(Math.cosh(Math.toRadians(val))));
						} else {
							jtf2.setText(truncator(Math.cosh(val)));
						}
					}
					if(function == "tanh") {
						if(radians == false) {
							jtf2.setText(truncator(Math.tanh(Math.toRadians(val))));
						} else {
							jtf2.setText(truncator(Math.tanh(val)));
						}
					}
					if(function == "sqr") {
						jtf2.setText(truncator(Math.pow(val, 2)));
					}
					if(function == "cube") {
						jtf2.setText(truncator(Math.pow(val, 3)));
					}
					if(function == "log") {
						jtf2.setText(truncator(Math.log10(val)));
					}
					if(function == "powten") {
						jtf2.setText(truncator(Math.pow(10, val)));
					}
					if(function == "cuberoot") {
						jtf2.setText(truncator(Math.cbrt(val)));
					}
					if(function == "fact") {
						jtf2.setText(truncator((val)));
					}
					if(function == "sqrt") {
						jtf2.setText(truncator(Math.sqrt(val)));
					}
					if(function == "reciproc") {
						double reciproc = (1 / val);
						jtf2.setText(truncator(reciproc));
					}
					if(function == "ln") {
						jtf2.setText(truncator(Math.log(val)));
					}
				}
			} 
			else {
				jtf1.setText(function + "(" + jtf2.getText() + ")");
				
				double val = Double.parseDouble(jtf2.getText());
				
				if(function == "sin") {
					if(radians == false) {
						jtf2.setText(truncator(Math.sin(Math.toRadians(val))));
					} else {
						jtf2.setText(truncator(Math.sin(val)));
					}
				}
				if(function == "cos") {
					if(radians == false) {
						jtf2.setText(truncator(Math.cos(Math.toRadians(val))));
					} else {
						jtf2.setText(truncator(Math.cos(val)));
					}
				}
				if(function == "tan") {
					if(radians == false) {
						jtf2.setText(truncator(Math.tan(Math.toRadians(val))));
					} else {
						jtf2.setText(truncator(Math.tan(val)));
					}
				}
				if(function == "sinh") {
					if(radians == false) {
						jtf2.setText(truncator(Math.sinh(Math.toRadians(val))));
					} else {
						jtf2.setText(truncator(Math.sinh(val)));
					}
				}
				if(function == "cosh") {
					if(radians == false) {
						jtf2.setText(truncator(Math.cosh(Math.toRadians(val))));
					} else {
						jtf2.setText(truncator(Math.cosh(val)));
					}
				}
				if(function == "tanh") {
					if(radians == false) {
						jtf2.setText(truncator(Math.tanh(Math.toRadians(val))));
					} else {
						jtf2.setText(truncator(Math.tanh(val)));
					}
				}
				if(function == "sqr") {
					jtf2.setText(truncator(Math.pow(val, 2)));
				}
				if(function == "cube") {
					jtf2.setText(truncator(Math.pow(val, 3)));
				}
				if(function == "log") {
					jtf2.setText(truncator(Math.log10(val)));
				}
				if(function == "powten") {
					jtf2.setText(truncator(Math.pow(10, val)));
				}
				if(function == "cuberoot") {
					jtf2.setText(truncator(Math.cbrt(val)));
				}
				if(function == "fact") {
					jtf2.setText(truncator((val)));
				}
				if(function == "sqrt") {
					jtf2.setText(truncator(Math.sqrt(val)));
				}
				if(function == "reciproc") {
					double reciproc = (1 / val);
					jtf2.setText(truncator(reciproc));
				}
				if(function == "ln") {
					jtf2.setText(truncator(Math.log(val)));
				}
			}
		}
	}
	//yroot
	//yroot
	private class yRootButton implements ActionListener {
		private JTextField jtf1, jtf2;
		
		public yRootButton(JTextField readout, JTextField entry) {
			this.jtf1 = readout;
			this.jtf2 = entry;
		}
		public void actionPerformed(ActionEvent ae) {
			if(!jtf1.getText().contains("yroot")) {
				jtf1.setText(jtf2.getText() + " " + "yroot" + " ");
			}
		}
	}
	//MOD
	//mod
	private class modButton implements ActionListener {
		private JTextField jtf1, jtf2;
		
		public modButton(JTextField readout, JTextField entry) {
			this.jtf1 = readout;
			this.jtf2 = entry;
		}
		public void actionPerformed(ActionEvent ae) {
			if(!jtf1.getText().contains("mod")) {
				jtf1.setText(jtf2.getText() + " " + "mod" + " ");
			}
		}
	}
	private class decimalButton implements ActionListener {
		private JTextField jtf;
		
		public decimalButton(JTextField jtf) {
			this.jtf = jtf;
		}
		public void actionPerformed(ActionEvent ae) {
			if(!jtf.getText().contains(".")) {
				jtf.setText(jtf.getText() + ".");
			}
		}
	}
	private class enterButton implements ActionListener {
		private JTextField jtf1, jtf2;
		
		public enterButton(JTextField readout, JTextField entry) {
			this.jtf1 = readout;
			this.jtf2 = entry;
		}
		public void actionPerformed(ActionEvent ae) {
			if(!jtf2.getText().equals("0")) {
				if(jtf1.getText().contains("mod")) {
					Scanner scan = new Scanner(jtf1.getText());
					String val = scan.next();
					
					double result = (Double.parseDouble(val) % Double.parseDouble(jtf2.getText()));
					
					jtf1.setText("");
					jtf2.setText(truncator(result));
					
					scan.close();
				} 
				if(jtf1.getText().contains("yroot")) {
					Scanner scan = new Scanner(jtf1.getText());
					String val = scan.next();
					
					double result = yroot(Double.parseDouble(val), Double.parseDouble(jtf2.getText()));
					
					jtf1.setText("");
					jtf2.setText(truncator(result));
					
					scan.close();
				} else {
					String expression = (jtf1.getText() + jtf2.getText());
					
					Calculations c = new Calculations(expression);
					
					jtf2.setText(c.Calculate());
				}
			}
		}
	}
	//About Menu
	private class aboutMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JDialog jd = new JDialog();
			jd.setTitle("About");
			jd.setSize(250, 200);
			jd.setLocationRelativeTo(Calculator.this);
			jd.setModal(true);
			
			JPanel jp = new JPanel();
			jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
			
			JLabel name = new JLabel("Raymond Dam");
			JLabel course = new JLabel("CSCI 201");
			JLabel semester = new JLabel("Spring 2014");
			JLabel icon = new JLabel(new ImageIcon("src/resources/myicon.png"));
			
			name.setAlignmentX(Component.CENTER_ALIGNMENT);
			course.setAlignmentX(Component.CENTER_ALIGNMENT);
			semester.setAlignmentX(Component.CENTER_ALIGNMENT);
			icon.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			jp.add(Box.createVerticalStrut(35));
			jp.add(icon);
			jp.add(Box.createVerticalStrut(10));
			jp.add(name);
			jp.add(course);
			jp.add(semester);
			
			jd.add(jp);
			jd.setVisible(true);
		}
	}
	private class piButton implements ActionListener {
		private JTextField jtf;
		
		public piButton(JTextField entry) {
			this.jtf = entry;
		}
		public void actionPerformed(ActionEvent e) {
			jtf.setText(Double.toString(Math.PI));
		}
	}
	//KEYLISTENERS
	
	//KEYDISPATCHER
	private class Dispatcher implements KeyEventDispatcher {
		private JTextField jtf1, jtf2;
		
		public Dispatcher(JTextField readout, JTextField entry) {
			this.jtf1 = readout;
			this.jtf2 = entry;
		}
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
            	//limit user entries
            	if(e.getKeyChar() == '1' || e.getKeyChar() == '2' || e.getKeyChar() == '3' || e.getKeyChar() == '4' || e.getKeyChar() == '5' ||
            		e.getKeyChar() == '6' || e.getKeyChar() == '7' || e.getKeyChar() == '8' || e.getKeyChar() == '9' || e.getKeyChar() == '0') {
            		if(!jtf2.getText().equals("0")) {
            			jtf2.setText(jtf2.getText() + e.getKeyChar());
            		} 
            		else {
            			jtf2.setText("" + e.getKeyChar());
            		}
            	}
            	//send symbol entries to the top display
            	if(e.getKeyChar() == '/' || e.getKeyChar() == '*' || e.getKeyChar() == '-' || e.getKeyChar() == '+') {
            		if(jtf2.getText().equals("0")) {
        				if(!jtf1.getText().isEmpty()) {
        					if(!((jtf1.getText().endsWith("/ ")) || (jtf1.getText().endsWith("* ")) || (jtf1.getText().endsWith("- ")) || (jtf1.getText().endsWith("+ ")) || (jtf1.getText().endsWith("^ ")))) {
        						jtf1.setText(jtf1.getText() + " " + e.getKeyChar() + " ");
        					}
        				} else {
        					jtf1.setText(jtf2.getText() + " " + e.getKeyChar() + " ");
        				}
        			} else {
        				if(jtf1.getText().isEmpty()) {
        					jtf1.setText(jtf2.getText() + " " + e.getKeyChar() + " ");
        				} else {
        					if(!((jtf1.getText().endsWith("/ ")) || (jtf1.getText().endsWith("* ")) || (jtf1.getText().endsWith("- ")) || (jtf1.getText().endsWith("+ ")) || (jtf1.getText().endsWith("^ ")))) {
        						jtf1.setText(jtf1.getText() + " " + e.getKeyChar() + " ");
        					}
        				}
        			}
            	}
            	//Equals button functionality
            	if(e.getKeyChar() == '=' || e.getKeyCode() == KeyEvent.VK_ENTER) {
            		if(!jtf2.getText().equals("0")) {
        				String expression = (jtf1.getText() + jtf2.getText());
        				
        				Calculations c = new Calculations(expression);
        				
        				jtf2.setText(c.Calculate());
        			}
            	}
            	//Delete button
            	if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            		if(!jtf2.getText().equals("0")) {
        				jtf2.setText(jtf2.getText().substring(0, jtf2.getText().length() - 1));
        			}
        			if(jtf2.getText().length() == 0) {
        				jtf2.setText("0");
        			}
            	}
            	//
            	if(e.getKeyCode() == KeyEvent.VK_PERIOD) {
            		if(!jtf2.getText().contains(".")) {
        				jtf2.setText(jtf2.getText() + ".");
        			}
            	}
            	if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            		jtf1.setText("");
        			jtf2.setText("0");
            	}
            } 
            return false;
        }
    }
	
	//returns a properly formated String for printing
	private String truncator(double input) {
		try {
			//do we need decimals?
			if(Math.floor(input) == input) {
			//convert double to integer
				int input2 = (int)input;
				String output = String.valueOf(input2);
				return output;
			}
			else {
				String output = String.valueOf(input);
				return output;
			}
		}
		catch(ArithmeticException ae) {
			return ("ArithmeticException: " + ae.getMessage());
		}
		catch(NumberFormatException nfe) {
			return ("NumberFormatException: " + nfe.getMessage());
		}
	}
	//Other Functions
	public static double yroot(double num, double root)
	{
	    return Math.pow(Math.exp (1/root),Math.log(num));
	} 

	public static void main(String [] args) {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } 
		catch (ClassNotFoundException cnfe) {
			cnfe.getMessage();
	    } 
		catch (InstantiationException ie) {
			ie.getMessage();
	    } 
		catch (IllegalAccessException iae) {
			iae.getMessage();
	    } 
		catch (UnsupportedLookAndFeelException ulafe) {
	        ulafe.getMessage();
	    }
		
		new Calculator();
	}
}