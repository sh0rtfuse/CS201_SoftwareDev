//Raymond Dam
//CSCI 201
//University of Southern California

package restuarantPackage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Restuarant extends JFrame {
	private static final long serialVersionUID = 1L;
	//Parser is declared here so that we can access parsed data
	private Parser p = new Parser();
	//Tracks Occupied and Open Tables
	private Vector<Table> openTables = new Vector<Table>();
	private Vector<Table> occupiedTables = new Vector<Table>();
	
	//=============HELPER METHODS=============//
	//take whatever is in open/occupied vectors and sort it
	private void reSort() {
		Vector<Integer> v1 = new Vector<Integer>();
		Vector<Integer> v2 = new Vector<Integer>();
		//load numbers on temp vectors
		for(int i = 0; i < openTables.size(); i++) {
			v1.add(openTables.get(i).getNumber());
		}
		
		for(int i = 0; i < openTables.size(); i++) {
			v2.add(occupiedTables.get(i).getNumber());
		}
		//sort data in open/occupied tables
		Collections.sort(v1);
		for(int i = 0; i < openTables.size(); i++) {
			for(int j = 0; j < v1.size(); j++) {
				if(openTables.get(i).getNumber() == v1.get(j)) {
					Table temp = openTables.get(j);
					openTables.set(j, openTables.get(i));
					openTables.set(i, temp);
				}
			}
		}
		Collections.sort(v2);
		for(int i = 0; i < occupiedTables.size(); i++) {
			for(int j = 0; j < v2.size(); j++) {
				if(occupiedTables.get(i).getNumber() == v2.get(j)) {
					Table temp = occupiedTables.get(j);
					occupiedTables.set(j, occupiedTables.get(i));
					occupiedTables.set(i, temp);
				}
			}
		}		
	}
	//separates occupied from open tables (initially only)
	private void initTableStatus() {
		//search xml data for open/occupied tables and store in appropriate file
		Vector<Integer> v1 = new Vector<Integer>();
		Vector<Integer> v2 = new Vector<Integer>();
		for(int i = 0; i < p.tables.size(); i++) {
			if(p.tables.get(i).isStatus() == true) {
				openTables.add(p.tables.get(i));
				v1.add(p.tables.get(i).getNumber());
			}
		}
		for(int i = 0; i < p.tables.size(); i++) {
			if(p.tables.get(i).isStatus() == false) {
				occupiedTables.add(p.tables.get(i));
				v2.add(p.tables.get(i).getNumber());
			}
		}
		//sort data in open/occupied tables
		Collections.sort(v1);		
		for(int i = 0; i < openTables.size(); i++) {
			for(int j = 0; j < v1.size(); j++) {
				if(openTables.get(i).getNumber() == v1.get(j)) {
					Table temp = openTables.get(j);
					openTables.set(j, openTables.get(i));
					openTables.set(i, temp);
				}
			}
		}
		Collections.sort(v2);
		for(int i = 0; i < occupiedTables.size(); i++) {
			for(int j = 0; j < v2.size(); j++) {
				if(occupiedTables.get(i).getNumber() == v2.get(j)) {
					Table temp = occupiedTables.get(j);
					occupiedTables.set(j, occupiedTables.get(i));
					occupiedTables.set(i, temp);
				}
			}
		}
	}
	//generic resort
	private Vector<Table> reSortOne(Vector<Table> v) {
		Vector<Integer> v1 = new Vector<Integer>();
		
		for(int i = 0; i < v.size(); i++) {
			v1.add(v.get(i).getNumber());
		}
		Collections.sort(v1);
		for(int i = 0; i < v.size(); i++) {
			for(int j = 0; j < v1.size(); j++) {
				if(v.get(i).getNumber() == v1.get(j)) {
					Table temp = v.get(j);
					v.set(j, v.get(i));
					v.set(i, temp);
				}
			}
		}
		return v;
	}
	//========================================//
	
	public Restuarant() {
		super("CSCI 201 Restuarant");
		setSize(800, 600);
		
		setJMenuBar(this.jmb());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String [] args) {
		try {
	        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
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
		
		new Restuarant();
	}
	
	//Core UI Elements
	private JMenuBar jmb() {
		JMenuBar jmb = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem open = new JMenuItem("Open");
		
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		open.addActionListener(new FileChooser());
		
		file.add(open);
		jmb.add(file);
		
		return jmb;
	}
	
	private class FileChooser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//create File Chooser Dialog Box
			JFileChooser jfc = new JFileChooser();
			jfc.setSize(600, 400);
			
			int result = jfc.showOpenDialog(Restuarant.this);
			
			if(result == JFileChooser.APPROVE_OPTION) {
				//Call XML Parser; throw dialog if file format is off
				try {
					SAXParserFactory spf = SAXParserFactory.newInstance();
				    spf.setNamespaceAware(true);
					SAXParser saxParser = spf.newSAXParser();
					saxParser.parse(jfc.getSelectedFile().getName(), p);
					
					//initial table status sort
					initTableStatus();
					
					add(initGUI());
					revalidate();
					repaint();
				}
				catch(SAXException saxe) {
					//call up error dialogbox
					JOptionPane.showMessageDialog(Restuarant.this, "Formatting Error: " + saxe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(IOException ioe) {
					//call up error dialogbox
					JOptionPane.showMessageDialog(Restuarant.this, "Formatting Error: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(ParserConfigurationException pce) {
					//call up error dialogbox
					JOptionPane.showMessageDialog(Restuarant.this, "Formatting Error: " + pce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			if(result == JFileChooser.CANCEL_OPTION) {
				//Close file chooser
			}
			
			jfc.setLocation(100, 100);
			jfc.setVisible(true);
		}
	}
	
	//JTable of # of tables/people
	private JScrollPane table(DefaultTableModel dtm) {
		JTable jt = new JTable(dtm);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.setTableHeader(null);
		jt.setRowHeight(20);
		jt.setFocusable(false);
		jt.setRowSelectionAllowed(false);
		//set font
		jt.setFont(p.rows.firstElement().getF());
		//set column width
		int width = p.columns.lastElement().getLocation().x - p.columns.firstElement().getLocation().x;
		for(int i = 0; i < jt.getColumnCount(); i++) {
			TableColumn col = jt.getColumnModel().getColumn(i);
			col.setPreferredWidth(width);
		}
		//modify viewport so JScrollPane fits perfectly
		int tableHeight = jt.getRowHeight() * 20;
		int tableWidth = width * p.columns.size();
		jt.setPreferredScrollableViewportSize(new Dimension(tableWidth, tableHeight));
		//scrollbar
		JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBorder(BorderFactory.createEmptyBorder());
		//set component location
		jsp.setBounds(p.columns.firstElement().getLocation().x, p.columns.firstElement().getLocation().y, tableWidth + 16, tableHeight);
		
		return jsp;
	}
	
	//initialize table and graphics after XML is loaded
	private JPanel initGUI() {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		
		//Initialize TableModel
		final DefaultTableModel dtm = new DefaultTableModel(p.rows.lastElement().getNumber(), p.columns.size()) {
			private static final long serialVersionUID = 1L;
			@Override //set all cells to non-editable
			public boolean isCellEditable(int row, int column) {
					return false;
			}
		};
		
		final JCheckBox open = new JCheckBox("Open Tables");
		open.setBounds(605, 450, 150, 15);
		
		final JCheckBox occupied = new JCheckBox("Occupied Tables");
		occupied.setBounds(605, 475, 200, 15);
		
		//actionlisteners
		open.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				if(ie.getStateChange() == ItemEvent.SELECTED) {
					//open: selected | occupied: not selected
					if(occupied.isSelected() == false) {
						//resort first
						reSort();
						//default view: open table displayed
						Object [][] data = new Object [openTables.size()][dtm.getColumnCount()];
						for(int i = 0; i < openTables.size(); i++) {
							String num = "Table " + openTables.get(i).getNumber();
							String people = Integer.toString(openTables.get(i).getNumseats());
							
							data [i][0] = num;
							data [i][1] = people;
						}	
						Object [] data2 = {" ", " "};
						dtm.setDataVector(data, data2);
						//add number of neccesary empty cells
						for(int i = 0; i < 20 - openTables.size(); i++) {
							dtm.addRow(data2);
						}
					}
					//open: selected | occupied: selected
					if(occupied.isSelected() == true) {
						Vector<Table> merge = new Vector<Table>();
						merge.addAll(0, openTables);
						merge.addAll(merge.size()-1, occupiedTables);
						//resort
						merge = reSortOne(merge);
						//add all to table
						Object [][] data = new Object [merge.size()][dtm.getColumnCount()];
						for(int i = 0; i < merge.size(); i++) {
							String num = "Table " + merge.get(i).getNumber();
							String people = Integer.toString(merge.get(i).getNumseats());
							
							data [i][0] = num;
							data [i][1] = people;
						}	
						Object [] data2 = {" ", " "};
						dtm.setDataVector(data, data2);
						//add number of neccesary empty cells
						for(int i = 0; i < 20 - merge.size(); i++) {
							dtm.addRow(data2);
						}
					}
				}
				if(ie.getStateChange() == ItemEvent.DESELECTED) {
					//open: not selected | occupied: not selected
					if(occupied.isSelected() == false) {
						dtm.getDataVector().removeAllElements();
						dtm.fireTableDataChanged();
						//add back empty cells
						Object [] data2 = {" ", " "};
						for(int i = 0; i < 20; i++) {
							dtm.addRow(data2);
						}
					}
					//open: not selected | occupied: selected
					if(occupied.isSelected() == true) {
						//resort first
						reSort();
						//show only occupied tables
						Object [][] data = new Object [occupiedTables.size()][dtm.getColumnCount()];
						for(int i = 0; i < occupiedTables.size(); i++) {
							String num = "Table " + occupiedTables.get(i).getNumber();
							String people = Integer.toString(occupiedTables.get(i).getNumseats());
							
							data [i][0] = num;
							data [i][1] = people;
						}	
						Object [] data2 = {" ", " "};
						dtm.setDataVector(data, data2);
						//add number of neccesary empty cells
						for(int i = 0; i < 20 - occupiedTables.size(); i++) {
							dtm.addRow(data2);
						}
					}
				}
			}	
		});
		open.setSelected(true);
		
		occupied.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				if(ie.getStateChange() == ItemEvent.SELECTED) {
					//open: not selected | occupied: selected
					if(open.isSelected() == false) {
						//resort first
						reSort();
						//show only occupied tables
						Object [][] data = new Object [occupiedTables.size()][dtm.getColumnCount()];
						for(int i = 0; i < occupiedTables.size(); i++) {
							String num = "Table " + occupiedTables.get(i).getNumber();
							String people = Integer.toString(occupiedTables.get(i).getNumseats());
							
							data [i][0] = num;
							data [i][1] = people;
						}	
						Object [] data2 = {" ", " "};
						dtm.setDataVector(data, data2);
						//add number of neccesary empty cells
						for(int i = 0; i < 20 - occupiedTables.size(); i++) {
							dtm.addRow(data2);
						}
					}
					//open: nselected | occupied: selected
					if(open.isSelected() == true) {
						Vector<Table> merge = new Vector<Table>();
						merge.addAll(0, openTables);
						merge.addAll(merge.size()-1, occupiedTables);
						//resort
						merge = reSortOne(merge);
						//add all to table
						Object [][] data = new Object [merge.size()][dtm.getColumnCount()];
						for(int i = 0; i < merge.size(); i++) {
							String num = "Table " + merge.get(i).getNumber();
							String people = Integer.toString(merge.get(i).getNumseats());
							
							data [i][0] = num;
							data [i][1] = people;
						}	
						Object [] data2 = {" ", " "};
						dtm.setDataVector(data, data2);
						//add number of neccesary empty cells
						for(int i = 0; i < 20 - merge.size(); i++) {
							dtm.addRow(data2);
						}
					}
				}
				if(ie.getStateChange() == ItemEvent.DESELECTED) {
					//open: not selected | occupied: not selected
					if(open.isSelected() == false) {
						dtm.getDataVector().removeAllElements();
						dtm.fireTableDataChanged();
						//add back empty cells
						Object [] data2 = {" ", " "};
						for(int i = 0; i < 20; i++) {
							dtm.addRow(data2);
						}
					}
					//open: selected | occupied: not selected
					if(open.isSelected() == true) {
						//resort first
						reSort();
						//default view: open table displayed
						Object [][] data = new Object [openTables.size()][dtm.getColumnCount()];
						for(int i = 0; i < openTables.size(); i++) {
							String num = "Table " + openTables.get(i).getNumber();
							String people = Integer.toString(openTables.get(i).getNumseats());
							
							data [i][0] = num;
							data [i][1] = people;
						}	
						Object [] data2 = {" ", " "};
						dtm.setDataVector(data, data2);
						//add number of neccesary empty cells
						for(int i = 0; i < 20 - openTables.size(); i++) {
							dtm.addRow(data2);
						}
					}
				}
			}	
		});
		
		//add Tables
		for(int i = 0; i < p.tables.size(); i++) {
			//check if round, square, or rectangle
			if(p.tables.get(i).getShape().equals("round")) {
				DrawRoundTable drt = new DrawRoundTable(p.tables.get(i));
				drt.setBounds(p.tables.get(i).getLocation().x, p.tables.get(i).getLocation().y, p.tables.get(i).getSize().width + 10, p.tables.get(i).getSize().width + 10);				
				jp.add(drt);
			}
			if(p.tables.get(i).getShape().equals("square")) {
				DrawSquareTable dst = new DrawSquareTable(p.tables.get(i));
				dst.setBounds(p.tables.get(i).getLocation().x, p.tables.get(i).getLocation().y, p.tables.get(i).getSize().width + 30, p.tables.get(i).getSize().width + 30);
				jp.add(dst);
			}
			if(p.tables.get(i).getShape().equals("rectangle")) {
				DrawRectTable dRectT = new DrawRectTable(p.tables.get(i));
				dRectT.setBounds(p.tables.get(i).getLocation().x, p.tables.get(i).getLocation().y - 10, p.tables.get(i).getSize().width + 30, p.tables.get(i).getSize().height + 30);
				jp.add(dRectT);
			}
		}
		
		//add Walls
		for(int i = 0; i < p.walls.size(); i++) {
			DrawWalls dw = new DrawWalls(p.walls.get(i));
			//calculate distance
			int distance = (int) p.walls.get(i).getStartLocation().distance(p.walls.get(i).getEndLocation());
			//Placement Logic
			if(p.walls.get(i).getStartLocation().x == p.walls.get(i).getEndLocation().x) {
				
				if(p.walls.get(i).getStartLocation().y > p.walls.get(i).getEndLocation().y) {
					dw.setBounds(p.walls.get(i).getEndLocation().x, p.walls.get(i).getEndLocation().y, 2, distance);
				}
				else if(p.walls.get(i).getStartLocation().y < p.walls.get(i).getEndLocation().y) {
					dw.setBounds(p.walls.get(i).getStartLocation().x, p.walls.get(i).getStartLocation().y, 2, distance);
				}
			}
			else if(p.walls.get(i).getStartLocation().y == p.walls.get(i).getEndLocation().y) {
				
				if(p.walls.get(i).getStartLocation().x > p.walls.get(i).getEndLocation().x) {
					dw.setBounds(p.walls.get(i).getEndLocation().x, p.walls.get(i).getEndLocation().y, distance, 2);
				}
				else if(p.walls.get(i).getStartLocation().x < p.walls.get(i).getEndLocation().x) {
					dw.setBounds(p.walls.get(i).getStartLocation().x, p.walls.get(i).getStartLocation().y, distance, 2);
				}
			}
			jp.add(dw);
		}
		
		//add Podium
		for(int i = 0; i < p.podiums.size(); i++) {
			DrawPodium pd = new DrawPodium(p.podiums.get(i));
			pd.setBounds(p.podiums.get(i).getLocation().x, p.podiums.get(i).getLocation().y, p.podiums.get(i).getSize().width, p.podiums.get(i).getSize().height);
			jp.add(pd);
		}
		
		//add title
		for(int i = 0; i < p.titles.size(); i++) {
			DrawTitle dt = new DrawTitle(p.titles.get(i));
			dt.setBounds(p.titles.get(i).getLocation().x, p.titles.get(i).getLocation().y, 300, 30);
			jp.add(dt);
		}
		
		//add table tiles
		for(int i = 0; i < p.columns.size(); i++) {
			DrawTableTitle dtt = new DrawTableTitle(p.columns.get(i));
			dtt.setBounds(p.columns.get(i).getLocation().x, p.columns.get(i).getLocation().y - 25, 75, 25);
			jp.add(dtt);
		}
		
		jp.add(open);
		jp.add(occupied);
		
		jp.add(this.table(dtm));
		
		return jp;
	}
}