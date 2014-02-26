package converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cartesian extends Coordinate{
	public Cartesian(double val1, double val2) {
		//instantiate the parent class constructor
		super(val1, val2);
	}

	public double getDistance(Coordinate c) {
		
		double distance = Math.sqrt(Math.pow((this.getValue1() - c.getValue1()), 2) + Math.pow((this.getValue2() - c.getValue2()), 2));
		
		return distance;
	}

	public double getSlopeOfLine(Coordinate c) {
		
		double slope = (this.getValue2() - c.getValue2()) / (this.getValue1() - c.getValue1());
		
		return slope;
	}
	
	public Polar converttoPolar() {
		
		double r = Math.pow((Math.pow(this.getValue1(), 2) + Math.pow(this.getValue2(), 2)), 0.5);
		double inRad = Math.atan2(this.getValue2(), this.getValue1());
		double theta = Math.toDegrees(inRad);
		
		if(theta < 0) {
			theta = theta + 360;
		}
				
		Polar p = new Polar(r, theta);
				
		return p;
	}
	
	public String getEquationoftheLine(double slope) {
		 //solve for y-intercept
		 double b = this.getValue2() - (slope * this.getValue1());
		 String output;
		  
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
