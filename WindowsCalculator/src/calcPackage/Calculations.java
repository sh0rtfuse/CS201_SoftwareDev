package calcPackage;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Calculations {
	//priority queue handles order of operations
	private Queue<Expressions> ePriorityQueue = new PriorityQueue<Expressions>(100, new parenthesis());
	
	//compares Integers assigned in the Expressions class to determine Queue Order
	private class parenthesis implements Comparator<Expressions> {
		public int compare(Expressions e1, Expressions e2) {
		       return Integer.compare(e1.getPriority(), e2.getPriority());
		}
	}
	
	public Calculations(String stringFromTextField) {
		//take input string and process it to be received by PQueue
		try {
			//Order operations priority
			Integer p = 0;
			Integer exp = 100;
			Integer mult = 200;
			Integer div = 300;
			Integer add = 400;
			Integer sub = 500;
			Integer misc = 600;
 			
			//add space to end
			stringFromTextField += " ";
			Scanner scan = new Scanner(stringFromTextField);
			
			while(scan.hasNext()) {
				String expression = scan.next();
				//parenthesis
				if(expression.startsWith("(")) {
					//grab rest enclosed in parenthesis
					expression += scan.useDelimiter(")");
					scan.useDelimiter(" ");
					//add to PQueue with appropriate priority
					ePriorityQueue.add(new Expressions(expression, p));
					p++;
					expression = scan.next();
				}
				//exponents
				if(expression.startsWith("sqr")) {
					ePriorityQueue.add(new Expressions(expression, exp));
					exp++;
				}
				//mult, add, div, sub
				if(expression.startsWith("0") || expression.startsWith("1") || expression.startsWith("2") || expression.startsWith("3") || expression.startsWith("4") || 
						expression.startsWith("5") || expression.startsWith("6") || expression.startsWith("7") || expression.startsWith("8") || expression.startsWith("9")) {
					String value = expression;
					
					if(scan.hasNext()) {
						String operand = scan.next();
						//check add to see if expression continues
						if(operand.equals("+")) {
							ePriorityQueue.add(new Expressions(value, add));
							add++;
						}
						if(operand.equals("-")) {
							ePriorityQueue.add(new Expressions(value, sub));
							sub++;
						}
						if(operand.equals("*")) {
							ePriorityQueue.add(new Expressions(value, mult));
							mult++;
						}
						if(operand.equals("/")) {
							ePriorityQueue.add(new Expressions(value, div));
							div++;
						}
					}
					//last value in the expression
					else {
						ePriorityQueue.add(new Expressions(value, misc));
						misc++;
					}
				}
				//handles situation after a parenthesis case mid-expression
				if(expression.equals(" ")) {
					//ignores
				}
				//handles expressions after parenthesis
				if(expression.equals("+") || expression.equals("-") || expression.equals("*") || expression.equals("/")) {
					String value = scan.next();
					
					if(expression.equals("+")) {
						ePriorityQueue.add(new Expressions(value, add));
						add++;
					}
					if(expression.equals("-")) {
						ePriorityQueue.add(new Expressions(value, sub));
						sub++;
					}
					if(expression.equals("*")) {
						ePriorityQueue.add(new Expressions(value, mult));
						mult++;
					}
					if(expression.equals("/")) {
						ePriorityQueue.add(new Expressions(value, div));
						div++;
					}
				}
				else {
					//
				}
			}
			
			scan.close();
		}
		catch(NoSuchElementException nsee) {
			//return an error to the JTextField
			nsee.getMessage();
		}
		catch(IllegalStateException ise) {
			//return an string to the JTextField
			ise.getMessage();
		}
	}
	//Methods to do calculation and return a new string of sum, difference, product, etc.
	public String Calculate() {
		try {
			Expressions e1, e2;
			String s1, s2;
			Integer i1, i2 = 9000;
			double val1, val2, result = 9000;
			
			while(!ePriorityQueue.isEmpty()) {
				e1 = ePriorityQueue.remove();
				s1 = e1.getExpression();
				i1 = e1.getPriority();
				
				//0 to 99 is parenthesis
				if(i1 >= 0 && i1 < 100) {
					
				}
				//100 to 199 is exponents
				if(i1 >= 100 && i1 < 200) {
					
				}
				//200 to 299 is multiplication
				if(i1 >= 200 && i1 < 300) {
					e2 = ePriorityQueue.remove();
					s2 = e2.getExpression();
					i2 = e2.getPriority();
					
					val1 = Double.parseDouble(s1);
					val2 = Double.parseDouble(s2);
					result = (val1 * val2);
				}
				//300 to 399 is division
				if(i1 >= 300 && i1 < 400) {
					e2 = ePriorityQueue.remove();
					s2 = e2.getExpression();
					i2 = e2.getPriority();
					
					val1 = Double.parseDouble(s1);
					val2 = Double.parseDouble(s2);
					result = (val1 / val2);
				}
				//400 to 499 is addition
				if(i1 >= 400 && i1 < 500) {
					e2 = ePriorityQueue.remove();
					s2 = e2.getExpression();
					i2 = e2.getPriority();
					
					val1 = Double.parseDouble(s1);
					val2 = Double.parseDouble(s2);
					result = (val1 + val2);
				}
				//500 to 599 is subtraction
				if(i1 >= 500 && i1 < 600) {
					e2 = ePriorityQueue.remove();
					s2 = e2.getExpression();
					i2 = e2.getPriority();
					
					val1 = Double.parseDouble(s1);
					val2 = Double.parseDouble(s2);
					result = (val1 - val2);
				}
				if(i1 <= 600) {
					//0 to 99 is parenthesis
					if(i2 >= 0 && i2 < 100) {
						
					}
					//100 to 199 is exponents
					if(i2 >= 100 && i2 < 200) {
						
					}
					//200 to 299 is multiplication
					if(i2 >= 200 && i2 < 300) {
						val1 = Double.parseDouble(s1);
						result = (result * val1);
					}
					//300 to 399 is division
					if(i2 >= 300 && i2 < 400) {
						val1 = Double.parseDouble(s1);
						result = (result / val1);
					}
					//400 to 499 is addition
					if(i2 >= 400 && i2 < 500) {
						val1 = Double.parseDouble(s1);
						result = (result + val1);
					}
					//500 to 599 is subtraction
					if(i2 >= 500 && i2 < 600) {
						val1 = Double.parseDouble(s1);
						result = (result - val1);
					}
				}
			}
			return truncator(result);
		}
		catch (NoSuchElementException nsee) {
			return "error!";
		}
	}
	
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
}