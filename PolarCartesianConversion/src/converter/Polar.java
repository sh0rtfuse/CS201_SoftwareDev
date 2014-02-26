package converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Polar extends Coordinate {
	public Polar(double val1, double val2) {
		//instantiate the parent constructor
		super(val1, val2);
	}

	public double getDistance(Coordinate c) {
		
		double toRad = Math.toRadians(this.getValue2() - c.getValue2());
		double distance = Math.sqrt(Math.pow(this.getValue1(), 2) + Math.pow(c.getValue1(), 2) - (2 * this.getValue1() * c.getValue1() * Math.cos(toRad)));
		
		return distance;
	}

	public double getSlopeOfLine(Coordinate c) {
		//convert both polar coordinates to the Cartesian system
		Cartesian converted1 = this.converttoCartesian();
		Cartesian converted2 = ((Polar)c).converttoCartesian();
		
		//use Cartesian coordinates to find the slope of the line
		double slope = (converted2.getValue2() - converted1.getValue2()) / (converted2.getValue1() - converted1.getValue1());
		
		return slope;
	}
	
	public Cartesian converttoCartesian() {
		
		double toRad = Math.toRadians(this.getValue2());
		double x = this.getValue1() * Math.cos(toRad);
		double y = this.getValue1() * Math.sin(toRad);
		
		Cartesian c = new Cartesian(x, y);
		
		return c;
	}
	
	public String getEquationoftheLine(double slope) {
		//add conversion to Cartesian
		Cartesian c = this.converttoCartesian();
		String output;
		
		//solve for y-intercept
		double b = c.getValue2() - (slope * c.getValue1());
		
		//add rounding functionality
		BigDecimal roundSlope = new BigDecimal(slope).setScale(2, RoundingMode.HALF_UP);
		slope = roundSlope.doubleValue();
		BigDecimal roundB = new BigDecimal(b).setScale(2, RoundingMode.HALF_UP);	
		b = roundB.doubleValue();
		//do we need decimals? both slope and y-intercept
		if(Math.floor(b) == b && Math.floor(slope) == slope) {
			int b2 = (int)b;
			int slope2 = (int)slope;
		
			if(b2 < 0) {
				//output a string in y = mx + b form
				output = ("y = " + slope2 + "x - " + (b2 * -1));
			}
			else {
				output = ("y = " + slope2 + "x + " + b2);
			}
		}
		//y-intercept only
		else if(Math.floor(b) == b) {
			//convert double to integer
			int b2 = (int)b;
			//is the number negative?
			if(b2 < 0) {
				//output a string in y = mx + b form
				output = ("y = " + slope + "x - " + (b2 * -1));
			}
			else {
				output = ("y = " + slope + "x + " + b2);
			}
		}
		//slope only
		else if(Math.floor(slope) == slope) {
			int slope2 = (int)slope;
					
			if(b < 0) {
				output = ("y = " + slope2 + "x - " + (b * -1));
			}
			else {
				output = ("y = " + slope2 + "x + " + b);
			}
		}
		//neither
		else {
			if(b < 0) {
				output = ("y = " + slope + "x - " + (b * -1));
			}
			else {
				output = ("y = " + slope + "x + " + b);
			}
		}			  
		
		return output;
	}
}