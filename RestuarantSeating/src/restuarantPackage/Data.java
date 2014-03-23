package restuarantPackage;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

//Modeling Classes
class Title {
	private String name;
	private Point location;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
}

//Column + Row are part of Tag "Tablestatus"
class Column {
	private String name;
	private Point location;
	private Font f;
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public Font getF() {
		return f;
	}
	public void setF(Font f) {
		this.f = f;
	}
}
	
class Rows {
	private int number;
	private int height;
	private Font f;
		
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Font getF() {
		return f;
	}
	public void setF(Font f) {
		this.f = f;
	}
}
////////////////////////////////////////

//Table, Wall, and Podium are part of tag "tables"
class Table {
	private int number;
	private int numseats;
	private String shape;
	private Point location;
	private Dimension size;			//If circle/square, then radius/length will occupy width field only
	private boolean status;			//open = true; occupied = false
		
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumseats() {
	return numseats;
	}
	public void setNumseats(int numseats) {
		this.numseats = numseats;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public Dimension getSize() {
		return size;
	}
	public void setSize(Dimension size) {
		this.size = size;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
	
class Wall {
	private Point startLocation;
	private Point endLocation;
		
	public Point getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(Point startLocation) {
		this.startLocation = startLocation;
	}
	public Point getEndLocation() {
		return endLocation;
	}
	public void setEndLocation(Point endLocation) {
		this.endLocation = endLocation;
	}
}
	
class Podium {
	private Point location;
	private Dimension size;
		
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public Dimension getSize() {
		return size;
	}
	public void setSize(Dimension size) {
		this.size = size;
	}
}