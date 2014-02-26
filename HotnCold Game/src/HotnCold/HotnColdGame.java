package HotnCold;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class HotnColdGame 
{
	public static void main(String [] args)
	{
		String repeat = "Y";
		
		//main program loop
		while (repeat.equals("Y"))
		{
			System.out.println("Welcome to the Hotter/Colder Game!");
			System.out.println();
			
			Scanner scan = new Scanner(System.in);
			
			System.out.print("How many rows are in the grid? ");
			int rows = 0;
			do		//input validation
			{
				if(rows < 0)
				{
					System.out.println("Entry must be nonnegative.");
					System.out.print("How many rows are in the grid? ");
				}	
				while(!scan.hasNextInt())
				{
					System.out.println("Entry is not a number.");
					System.out.print("How many rows are in the grid? ");
					scan.next();
				}
				rows = scan.nextInt();
			}
			while (rows < 0);

			System.out.print("How many columns are in the grid? ");
			int columns = 0;
			do		//input validation
			{	
				if(columns < 0)
				{
					System.out.println("Entry must be nonnegative.");
					System.out.print("How many columns are in the grid? ");
				}	
				while(!scan.hasNextInt())
				{
					System.out.println("Entry is not a number.");
					System.out.print("How many columns are in the grid? ");
					scan.next();
				}
				columns = scan.nextInt();
			}
			while (columns < 0);
			
			System.out.print("Do you have anything else to tell me? ");
			String admin = scan.next();
			
			//call Game method to beginning the game
			Game(rows, columns, admin);
			
			System.out.print("Would you like to play again (Y/N)? ");
			repeat = scan.next();
			while ((!repeat.equals("Y")) && (!repeat.equals("N")))		//input validation
			{
				System.out.println("Entry must be 'Y' or 'N'");
				System.out.print("Would you like to play again (Y/N)? ");
				repeat = scan.next();
			}
		}
	}
	
	private static void Game(int rows, int columns, String admin)
	{
		//Generate random location
		Random rand = new Random();
		int randRow = rand.nextInt((rows - 0) + 1) + 0;
		int randCol = rand.nextInt((columns - 0) + 1) + 0;
		
		//Testing Functionality
		if(admin.equals("A"))
		{
			System.out.println("Ahh, you’re an administrator. The random location is " + randRow + ", " + randCol);
		}
		
		Scanner scan = new Scanner(System.in);
		int CurrentRow, CurrentCol;
		
		System.out.print("What is your first guess? ");
		CurrentRow = scan.nextInt();
		CurrentCol = scan.nextInt();
		
		double PreviousDistance = DistanceCalc(randRow, randCol, CurrentRow, CurrentCol);
		
		//add truncating functionality
		DecimalFormat truncate = new DecimalFormat("#0.00");
		truncate.setRoundingMode(RoundingMode.HALF_UP);
		
		if(admin.equals("A"))
		{
			System.out.println("Distance = " + truncate.format(PreviousDistance));
		}
		
		//loop until user guesses correctly
		boolean GameOver = false;
		
		//if user guesses correct on first try
		if(PreviousDistance == 0)
		{
			System.out.println("YOU FOUND IT!");
			
			//skip the loop
			GameOver = true;
		}
		
		while (GameOver == false)
		{
			System.out.print("What is your next guess? ");
			CurrentRow = scan.nextInt();
			CurrentCol = scan.nextInt();
			
			//call method to calculate distance
			double distance = DistanceCalc(randRow, randCol, CurrentRow, CurrentCol);
			
			if(admin.equals("A"))
			{
				System.out.println("Distance = " + truncate.format(distance));
			}
			
			//if/else if statements handle program response
			if((PreviousDistance > distance) && (distance > 1.42))
			{
				System.out.println("You’re getting warmer.");
			}		
			else if((PreviousDistance < distance) && (distance > 1.42))
			{
				System.out.println("You’re getting colder.");
			}
			else if(distance == PreviousDistance)
			{
				System.out.println("You’re not getting warmer or colder.");
			}
			else if((distance <= 1.42) && (distance > 0))
			{
				System.out.println("You’re on fire!");
			}
			else if(distance == 0)
			{
				System.out.println("YOU FOUND IT!");
				
				//exit the loop
				GameOver = true;
			}

			//update previous distance
			PreviousDistance = distance;
		}
	}
	
	//Takes in two coordinates and returns a double of the distance
	private static double DistanceCalc(int y1, int x1, int y2, int x2)
	{
		double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		
		return distance;
	}
	
}