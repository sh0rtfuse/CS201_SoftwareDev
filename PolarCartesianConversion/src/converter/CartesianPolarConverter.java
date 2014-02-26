package converter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CartesianPolarConverter {
	public static void main(String [] args) {
		mainmenu();
	}
	
	private static void mainmenu() {
		Scanner scan = new Scanner(System.in);
		boolean end = false;
		
		while(end == false) {
			try {
				System.out.println(" ");
				System.out.println("[polar] Polar Coordinates (r, theta)");
				System.out.println("[cartesian] Cartesian Coordinates (x, y)");
				System.out.println("[file] File Input");
				System.out.println("[exit] Exit program");
				System.out.println(" ");
				System.out.print("What type of coordinates? ");
				String input = scan.nextLine();
				//switching with String type requires Java 1.7
				switch(input) {
					case "polar":
						degreeORradian();
						end = true;
						break;
					case "cartesian":
						cartesianEntry();
						end = true;
						break;
					case "file":
						System.out.println(" ");
						System.out.print("Enter the input filename: ");
						String inputFile = scan.nextLine();
						System.out.print("Enter the output filename: ");
						String outputFile = scan.nextLine();
						fileIO(inputFile, outputFile);
						break;
					case "exit":
						System.out.println(" ");
						System.out.println("Thank you for using my program!");
						end = true;
						break;
					default:
						System.out.println(" ");
						System.out.println("Please enter one of the options provided.");
						break;
				}
			}
			catch(InputMismatchException ime) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
			catch(NoSuchElementException nsee) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
			catch(IllegalStateException ise) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
		}
		
		scan.close();
	}
	
	private static void degreeORradian() {
		Scanner scan = new Scanner(System.in);
		boolean end = false;
		
		while(end == false) {
			try {
				System.out.println(" ");
				System.out.println("[degrees] Degrees");
				System.out.println("[radians] Radians");
				System.out.println(" ");
				System.out.print("What type of angle? ");
				String input = scan.nextLine();
				
				switch(input) {
					case "degrees":
						polarEntry(false);
						end = true;
						break;
					case "radians":
						polarEntry(true);
						end = true;
						break;
					default:
						System.out.println(" ");
						System.out.println("Please enter one of the options provided.");
						break;
				}
			}
			catch(InputMismatchException ime) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
			catch(NoSuchElementException nsee) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
			catch(IllegalStateException ise) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
		}
		
		scan.close();
	}
	
	private static void polarEntry(boolean radian) {
		Scanner scan = new Scanner(System.in);
		boolean end = false;
		
		while(end == false) {
			try {
				double radius1, angle1, radius2, angle2;
				
				if(radian == true) {
					System.out.println(" ");
					System.out.print("Coordinate 1 - Please enter the radius: ");
					radius1 = scan.nextDouble();
					System.out.print("Coordinate 1 - Please enter theta (radians): ");
					angle1 = scan.nextDouble();
					System.out.print("Coordinate 2 - Please enter the radius: ");
					radius2 = scan.nextDouble();
					System.out.print("Coordinate 2 - Please enter theta (radians): ");
					angle2 = scan.nextDouble();
					
					//convert radians to degrees
					double degree1 = angle1 * (180 / Math.PI);
					double degree2 = angle2 * (180 / Math.PI);
					
					//end the loop and call convertMenu
					convertMenu(radius1, degree1, radius2, degree2, true);
					end = true;
				}
				else {
					System.out.println(" ");
					System.out.print("Coordinate 1 - Please enter the radius: ");
					radius1 = scan.nextDouble();
					System.out.print("Coordinate 1 - Please enter theta (degrees): ");
					angle1 = scan.nextDouble();
					System.out.print("Coordinate 2 - Please enter the radius: ");
					radius2 = scan.nextDouble();
					System.out.print("Coordinate 2 - Please enter theta (degrees): ");
					angle2 = scan.nextDouble();
					
					convertMenu(radius1, angle1, radius2, angle2, true);
					end = true;
				}
			} 
			catch(InputMismatchException ime) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();	//skips the bad token
			}
			catch(NoSuchElementException nsee) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
			catch(IllegalStateException ise) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
		}
		
		scan.close();
	}
	
	private static void cartesianEntry() {
		Scanner scan = new Scanner(System.in);
		boolean end = false;
		
		while(end == false) {
			try {
				double x1, y1, x2, y2;
				
				System.out.println(" ");
				System.out.print("Coordinate 1 - Please enter x: ");
				x1 = scan.nextDouble();
				System.out.print("Coordinate 1 - Please enter y: ");
				y1 = scan.nextDouble();
				System.out.print("Coordinate 2 - Please enter x: ");
				x2 = scan.nextDouble();
				System.out.print("Coordinate 2 - Please enter y: ");
				y2 = scan.nextDouble();
				
				convertMenu(x1, y1, x2, y2, false);
				end = true;
			}
			catch(InputMismatchException ime) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
			catch(NoSuchElementException nsee) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
			catch(IllegalStateException ise) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
		}
		
		scan.close();
	}
	
	private static void convertMenu(double coord1A, double coord1B, double coord2A, double coord2B, boolean polar) {
		Scanner scan = new Scanner(System.in);
		boolean end = false;
		
		while(end == false) {
			String input;
			Coordinate coord1;
			Coordinate coord2;
			
			try {
				if(polar == true) {
					System.out.println(" ");
					System.out.println("[convert] Convert to Cartesian coordinates");
					System.out.println("[distance] Find the distance between the two points");
					System.out.println("[slope] Find the slope of the line between the points");
					System.out.println("[equation] Find the equation of the line between the points");
					System.out.println("[menu] Return to main menu");
					System.out.println(" ");
					System.out.print("What would you like to do? ");
					input = scan.nextLine();
					
					//declare instances of Coordinate instantiated as Polar
					coord1 = new Polar(coord1A, coord1B);
					coord2 = new Polar(coord2A, coord2B);
				}
				else {
					System.out.println(" ");
					System.out.println("[convert] Convert to Polar coordinates");
					System.out.println("[distance] Find the distance between the two points");
					System.out.println("[slope] Find the slope of the line between the points");
					System.out.println("[equation] Find the equation of the line between the points");
					System.out.println("[menu] Return to main menu");
					System.out.println(" ");
					System.out.print("What would you like to do? ");
					input = scan.nextLine();
					
					//declare instances of Coordinate instantiated as Cartesian
					coord1 = new Cartesian(coord1A, coord1B);
					coord2 = new Cartesian(coord2A, coord2B);
				}
				
				switch(input) {
					case "convert":
						//Polar to Cartesian or vice versa 
						if(polar == true) {
							Cartesian c1 = ((Polar)coord1).converttoCartesian();
							Cartesian c2 = ((Polar)coord2).converttoCartesian();
							
							System.out.println(" ");
							System.out.println("The Cartesian coordinate for (" + truncator(coord1.getValue1()) + ", " + truncator(coord1.getValue2()) + ") is (" + truncator(c1.getValue1()) + ", " + truncator(c1.getValue2()) + ")");
							System.out.println("The Cartesian coordinate for (" + truncator(coord2.getValue1()) + ", " + truncator(coord2.getValue2()) + ") is (" + truncator(c2.getValue1()) + ", " + truncator(c2.getValue2()) + ")");
						} 
						else {
							Polar p1 = ((Cartesian)coord1).converttoPolar();
							Polar p2 = ((Cartesian)coord2).converttoPolar();
							
							System.out.println(" ");
							System.out.println("The Polar coordinate for (" + truncator(coord1.getValue1()) + ", " + truncator(coord1.getValue2()) + ") is (" + truncator(p1.getValue1()) + ", " + truncator(p1.getValue2()) + ")");
							System.out.println("The Polar coordinate for (" + truncator(coord2.getValue1()) + ", " + truncator(coord2.getValue2()) + ") is (" + truncator(p2.getValue1()) + ", " + truncator(p2.getValue2()) + ")");
						}
						break;
					case "distance":
						double distance = coord1.getDistance(coord2);
						if(polar == true) {
							System.out.println(" ");
							System.out.println("The distance between the Polar coordinates (" + truncator(coord1.getValue1()) + ", " + truncator(coord1.getValue2()) + ") and (" + truncator(coord2.getValue1()) + ", " + truncator(coord2.getValue2()) + ") is " + truncator(distance));
						}
						else {
							System.out.println(" ");
							System.out.println("The distance between the Cartesian coordinates (" + truncator(coord1.getValue1()) + ", " + truncator(coord1.getValue2()) + ") and (" + truncator(coord2.getValue1()) + ", " + truncator(coord2.getValue2()) + ") is " + truncator(distance));
						}
						break;
					case "slope":
						double slope = coord1.getSlopeOfLine(coord2);
						if(polar == true) {
							System.out.println(" ");
							System.out.println("The slope of the line between the Polar coordinates (" + truncator(coord1.getValue1()) + ", " + truncator(coord1.getValue2()) + ") and (" + truncator(coord2.getValue1()) + ", " + truncator(coord2.getValue2()) + ") is " + truncator(slope));
						}
						else {
							System.out.println(" ");
							System.out.println("The slope of the line between the Cartesian coordinates (" + truncator(coord1.getValue1()) + ", " + truncator(coord1.getValue2()) + ") and (" + truncator(coord2.getValue1()) + ", " + truncator(coord2.getValue2()) + ") is " + truncator(slope));
						}
						break;
					case "equation":
						double slope2 = coord1.getSlopeOfLine(coord2);
						String equation = coord1.getEquationoftheLine(slope2);
						
						System.out.println(" ");
						System.out.println("The equation of the line between the points is " + equation);
						break;
					case "menu":
						mainmenu();
						end = true;
						break;
					default:
						System.out.println(" ");
						System.out.println("Please enter one of the options provided.");
						break;
				}
			}
			catch(InputMismatchException ime) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
			catch(NoSuchElementException nsee) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
			catch(IllegalStateException ise) {
				System.out.println(" ");
				System.out.println("Please enter one of the options provided.");
				scan.nextLine();
			}
		}
		
		scan.close();
	}

	//returns a properly formated String for printing
	private static String truncator(double input) {
		try {
			//rounding functionality
			BigDecimal roundInput = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
			input = roundInput.doubleValue();
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
	
	private static void fileIO(String input, String output) {
		try {
			FileReader fr = new FileReader(input);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			
			FileWriter fw = new FileWriter(output);
			PrintWriter pw = new PrintWriter(fw);
			
			while(line != null) {
				String conv1, conv2, prtDist, prtSlope, prtEquation;
				String angType = null;
				boolean polar = false; 
				Coordinate coord1;
				Coordinate coord2;
				
				//grab only one set of coordinates
				String coordType = line;
				line = br.readLine();
				String coordinate1 = line;
				line = br.readLine();
				String coordinate2 = line;
				
				//parse and convert coordinates
				StringTokenizer st2 = new StringTokenizer(coordinate1);
				String strval1a = st2.nextToken(",");
				String strval1b = st2.nextToken();
				double coord1A = Double.parseDouble(strval1a);
				double coord1B = Double.parseDouble(strval1b);
				
				StringTokenizer st3 = new StringTokenizer(coordinate2);
				String strval2a = st3.nextToken(",");
				String strval2b = st3.nextToken();
				double coord2A = Double.parseDouble(strval2a);
				double coord2B = Double.parseDouble(strval2b);	
				
				//determine coordinate type
				StringTokenizer st = new StringTokenizer(coordType);
				if(st.countTokens() == 2) {
					st.nextToken(",");
					angType = st.nextToken();
					polar = true;
				}
				
				//output based on coordinate type  
				if (polar == true) {
					//if radians convert to degrees
					double degree1 = 0, degree2 = 0;
					if(angType.equals("Radians")) {
						degree1 = coord1B * (180 / Math.PI);
						degree2 = coord2B * (180 / Math.PI);
						
						coord1 = new Polar(coord1A, degree1);
						coord2 = new Polar(coord2A, degree2);
					}
					else {
						coord1 = new Polar(coord1A, coord1B);
						coord2 = new Polar(coord2A, coord2B);
					}
					
					//convert
					Cartesian c1 = ((Polar)coord1).converttoCartesian();
					Cartesian c2 = ((Polar)coord2).converttoCartesian();

					conv1 = ("The Cartesian coordinate for (" + truncator(coord1.getValue1()) + ", " + truncator(coord1.getValue2()) + ") is (" + truncator(c1.getValue1()) + ", " + truncator(c1.getValue2()) + ")");
					conv2 = ("The Cartesian coordinate for (" + truncator(coord2.getValue1()) + ", " + truncator(coord2.getValue2()) + ") is (" + truncator(c2.getValue1()) + ", " + truncator(c2.getValue2()) + ")");
					
					//distance
					double distance = coord1.getDistance(coord2);
					prtDist = ("The distance between the Polar coordinates (" + truncator(coord1.getValue1()) + ", " + truncator(coord1.getValue2()) + ") and (" + truncator(coord2.getValue1()) + ", " + truncator(coord2.getValue2()) + ") is " + truncator(distance));
					
					//slope
					double slope = coord1.getSlopeOfLine(coord2);
					prtSlope = ("The slope of the line between the Polar coordinates (" + truncator(coord1.getValue1()) + ", " + truncator(coord1.getValue2()) + ") and (" + truncator(coord2.getValue1()) + ", " + truncator(coord2.getValue2()) + ") is " + truncator(slope));
					
					//equation of the line
					double slope2 = coord1.getSlopeOfLine(coord2);
					String equation = coord1.getEquationoftheLine(slope2);
					
					prtEquation = ("The equation of the line between the points is " + equation);
				}
				else {
					coord1 = new Cartesian(coord1A, coord1B);
					coord2 = new Cartesian(coord2A, coord2B);
					
					//convert
					Polar p1 = ((Cartesian)coord1).converttoPolar();
					Polar p2 = ((Cartesian)coord2).converttoPolar();

					conv1 = ("The Polar coordinate for (" + truncator(coord1.getValue1()) + ", " + truncator(coord1.getValue2()) + ") is (" + truncator(p1.getValue1()) + ", " + truncator(p1.getValue2()) + ")");
					conv2 = ("The Polar coordinate for (" + truncator(coord2.getValue1()) + ", " + truncator(coord2.getValue2()) + ") is (" + truncator(p2.getValue1()) + ", " + truncator(p2.getValue2()) + ")");
					
					//distance
					double distance = coord1.getDistance(coord2);
					prtDist = ("The distance between the Cartesian coordinates (" + truncator(coord1.getValue1()) + ", " + truncator(coord1.getValue2()) + ") and (" + truncator(coord2.getValue1()) + ", " + truncator(coord2.getValue2()) + ") is " + truncator(distance));
					
					//slope
					double slope = coord1.getSlopeOfLine(coord2);
					prtSlope = ("The slope of the line between the Cartesian coordinates (" + truncator(coord1.getValue1()) + ", " + truncator(coord1.getValue2()) + ") and (" + truncator(coord2.getValue1()) + ", " + truncator(coord2.getValue2()) + ") is " + truncator(slope));
					
					//equation of the line
					double slope2 = coord1.getSlopeOfLine(coord2);
					String equation = coord1.getEquationoftheLine(slope2);
					
					prtEquation = ("The equation of the line between the points is " + equation);
				}
				
				//add everything to output file
				pw.println();
				pw.println(conv1);
				pw.println(conv2);
				pw.println();
				pw.println(prtDist);
				pw.println();
				pw.println(prtSlope);
				pw.println();
				pw.println(prtEquation);
				pw.println();
				pw.println("---");
				
				line = br.readLine();
			}
			
			pw.close();
			fw.close();
			br.close();
			fr.close();
			
			System.out.println(" ");
			System.out.println("File was parsed and output generated.");
		}
		catch(FileNotFoundException fnfe) {
			System.out.println(" ");
			System.out.println("FileNotFoundException: " + fnfe.getMessage());
			mainmenu();
		}
		catch(IOException ioe) {
			System.out.println(" ");
			System.out.println("IOException: " + ioe.getMessage());
			mainmenu();
		}
	}

}