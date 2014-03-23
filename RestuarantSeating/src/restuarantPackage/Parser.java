package restuarantPackage;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Parser extends DefaultHandler {
	//All data from xml file is stored here
	protected Vector<Title> titles = new Vector<Title>();
	protected Vector<Column> columns = new Vector<Column>();
	protected Vector<Rows> rows = new Vector<Rows>();
	protected Vector<Table> tables = new Vector<Table>();
	protected Vector<Wall> walls = new Vector<Wall>();
	protected Vector<Podium> podiums = new Vector<Podium>();
	//Buffers Element
	private String buffer;
	//Temporary storage
	private Title tempT;
	private Column tempCol;
	private Rows tempRow;
	private Table tempTab;
	private Wall tempW;
	private Podium tempP;
	private Point p = new Point();
	private Dimension d = new Dimension();
	//Stack handles tags with different parent tags
	private Stack<String> tags = new Stack<String>();
	
	//helper methods
	private Font toFont(String name, String type, String size) {
		//check type for bold, italic, or plain
		int style = 0;
		
		if(type.equals("bold")) { style = Font.BOLD; }
		if(type.equals("italic")) { style = Font.ITALIC; }
		if(type.equals("plain")) { style = Font.PLAIN; }
		
		Font f = new Font(name, style, Integer.parseInt(size));
		return f;
	}
	private boolean isOpen(String s) {
		boolean b = true;
		
		if(s.equals("open")) { b = true; }
		if(s.equals("occupied")) { b = false; }
		
		return b;
	}
	
	@Override
	public void startDocument() throws SAXException {
		
	}
	
	@Override
	public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {	
		//code to handle each tag; level 2 tags clears structure that hold sub tags
		if(qName.equalsIgnoreCase("title")) {
			tempT = new Title();
			tags.push(qName);
		}
		if(qName.equalsIgnoreCase("column")) {
			tempCol = new Column();
			tags.push(qName);
			
			tempCol.setName(attributes.getValue("name"));
		}
		if(qName.equalsIgnoreCase("rows")) {
			tempRow = new Rows();
			tags.push(qName);
			
			tempRow.setNumber(Integer.parseInt(attributes.getValue("number")));
			tempRow.setHeight(Integer.parseInt(attributes.getValue("height")));
		}
		if(qName.equalsIgnoreCase("table")) {
			tempTab = new Table();
			tags.push(qName);
			
			tempTab.setNumber(Integer.parseInt(attributes.getValue("number")));
		}
		if(qName.equalsIgnoreCase("wall")) {
			tempW = new Wall();
			tags.push(qName);
		}
		if(qName.equalsIgnoreCase("podium")) {
			tempP = new Podium();
			tags.push(qName);
		}
		//elementless tags
		if(qName.equalsIgnoreCase("font")) {
			if(tags.peek() == "column") {
				tempCol.setF(toFont(attributes.getValue("name"), attributes.getValue("type"), attributes.getValue("size")));
			}
			if(tags.peek() == "rows") {
				tempRow.setF(toFont(attributes.getValue("name"), attributes.getValue("type"), attributes.getValue("size")));
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		try {
			if(qName.equalsIgnoreCase("name")) {
				if(tags.peek() == "title") {
					tempT.setName(buffer);
				}
			}
			if(qName.equalsIgnoreCase("x")) {
				if(tags.peek() == "title") {
					p.x = Integer.parseInt(buffer);
				}
				if(tags.peek() == "column") {
					p.x = Integer.parseInt(buffer);
				}
				if(tags.peek() == "table") {
					p.x = Integer.parseInt(buffer);
				}
				if(tags.peek() == "wall") {
					p.x = Integer.parseInt(buffer);
				}
				if(tags.peek() == "podium") {
					p.x = Integer.parseInt(buffer);
				}
			}
			if(qName.equalsIgnoreCase("y")) {
				if(tags.peek() == "title") {
					p.y = Integer.parseInt(buffer);					
					//flush p for next set of points
					tempT.setLocation(p);
					p = new Point();
				}
				if(tags.peek() == "column") {
					p.y = Integer.parseInt(buffer);					
					tempCol.setLocation(p);
					p = new Point();
				}
				if(tags.peek() == "table") {
					p.y = Integer.parseInt(buffer);
					tempTab.setLocation(p);
					p = new Point();
				}
				if(tags.peek() == "wall") {
					p.y = Integer.parseInt(buffer);
					//we don't flush the point until start/end location tag
				}
				if(tags.peek() == "podium") {
					p.y = Integer.parseInt(buffer);
					tempP.setLocation(p);
					p = new Point();
				}
			}
			if(qName.equalsIgnoreCase("numseats")) {
				if(tags.peek() == "table") {
					tempTab.setNumseats(Integer.parseInt(buffer));
				}
			}
			if(qName.equalsIgnoreCase("shape")) {
				if(tags.peek() == "table") {
					tempTab.setShape(buffer);
				}
			}
			//handles square/circle table sizes only
			if(qName.equalsIgnoreCase("size")) {
				if(tags.peek() == "table") {
					//checks to see if element is an int (circle/square) or not (rectangle)
					Scanner scan = new Scanner(buffer);
					if(scan.hasNextInt()) {
						tempTab.setSize(new Dimension(Integer.parseInt(buffer), -1));
					}
					scan.close();
				}
			}
			//handles rectangle tables sizes only + podium
			if(qName.equalsIgnoreCase("width")) {
				if(tags.peek() == "table") {
					d.width = Integer.parseInt(buffer);
				}
				if(tags.peek() == "podium") {
					d.width = Integer.parseInt(buffer);
				}
			}
			if(qName.equalsIgnoreCase("height")) {
				if(tags.peek() == "table") {
					d.height = Integer.parseInt(buffer);
					//flush d for next set of dimensions
					tempTab.setSize(d);
					d = new Dimension();
				}
				if(tags.peek() == "podium") {
					d.height = Integer.parseInt(buffer);
					tempP.setSize(d);
					d = new Dimension();
				}
			}
			if(qName.equalsIgnoreCase("startlocation")) {
				if(tags.peek() == "wall") {
					tempW.setStartLocation(p);
					//for walls, p gets flushed here
					p = new Point();
				}
			}
			if(qName.equalsIgnoreCase("endlocation")) {
				if(tags.peek() == "wall") {
					tempW.setEndLocation(p);
					p = new Point();
				}
			}
			if(qName.equalsIgnoreCase("status")) {
				if(tags.peek() == "table") {
					boolean b = isOpen(buffer);
					tempTab.setStatus(b);
				}
			}
			//end tag: add title to vector & pop stack
			if(qName.equalsIgnoreCase("title")) {
				titles.add(tempT);
				tags.pop();
			}
			if(qName.equalsIgnoreCase("column")) {
				columns.add(tempCol);
				tags.pop();
			}
			if(qName.equalsIgnoreCase("rows")) {
				rows.add(tempRow);
				tags.pop();
			}
			if(qName.equalsIgnoreCase("table")) {
				tables.add(tempTab);
				tags.pop();
			}
			if(qName.equalsIgnoreCase("wall")) {
				walls.add(tempW);
				tags.pop();
			}
			if(qName.equalsIgnoreCase("podium")) {
				podiums.add(tempP);
				tags.pop();
			}
		}
		catch(EmptyStackException ese) {
			throw new SAXException(ese.getMessage());
		}
		catch(NullPointerException npe) {
			throw new SAXException(npe.getMessage());
		}
		catch(NumberFormatException nfe) {
			throw new SAXException(nfe.getMessage());
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		buffer = new String(ch, start, length);
	}
}