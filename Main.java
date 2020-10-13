import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException,IOException
	{
		//File reader that will read in the text document
		File inputFile = new File("input.txt");
		Scanner in = new Scanner(inputFile);
		
		//Creates the big integers and stores it while reading in the file
		BigDecimal userNum = new BigDecimal( in.nextLine() );
		BigDecimal userDeno = new BigDecimal( in.nextLine() );
		
		in.close();
		
		//Lists to store and hold the numerator,denominator, and the list of values
		ArrayList<BigDecimal> num = new ArrayList<>();
		ArrayList<BigDecimal> deno = new ArrayList<>();
		
		num.add( new BigDecimal("0") );
		num.add( new BigDecimal("1") );
		num.add( new BigDecimal("1") );
		
		deno.add( new BigDecimal("1") );
		deno.add( new BigDecimal("1") );
		deno.add( new BigDecimal("0") );
		
		//Creation of basic variables that will be used in the while loop
		BigDecimal squareNum = num.get(1).multiply( num.get(1) );
		BigDecimal squareDeno = deno.get(1).multiply( deno.get(1) );
		BigDecimal userDenoTimesSquareNum = new BigDecimal(0);
		BigDecimal userNumTimesSquareDeno = new BigDecimal(0);
		BigDecimal numbersMinused = userDenoTimesSquareNum.min(userNumTimesSquareDeno);
		
		boolean answerFound = false;
		BigDecimal answerNum = new BigDecimal(0);
		BigDecimal answerDeno = new BigDecimal(0);
	
		//Will continue to loop until the answer is found in the tree
		while(!answerFound)
		{
			//Creation of list and creation of new values to add to list
			for(int i=0; i < Math.ceil(num.size() /2 ) ; i++)
			{

				num.add(2*i+1, num.get(2*i).add(num.get(2*i+1) ) );
				deno.add(2*i+1, deno.get(2*i).add(deno.get(2*i+1) ) );
				
			}//End of for loop
			
			//Checking for valid number in the list
			for(int i=1; i < num.size()-1 ; i++)
			{
				//This is where all the math is done for the inequality provided
				squareNum = num.get(i).multiply( num.get(i) );
				squareDeno = deno.get(i).multiply( deno.get(i) );
				userDenoTimesSquareNum = userDeno.multiply(squareNum);
				userNumTimesSquareDeno = userNum.multiply(squareDeno);
				numbersMinused = userDenoTimesSquareNum.subtract(userNumTimesSquareDeno);
				numbersMinused = numbersMinused.abs();
				
				//Checks to see if checked number is the solution or not
				if( numbersMinused.compareTo(deno.get(i)) == -1 || numbersMinused.compareTo(deno.get(i)) == 0)
				{
					answerFound=true;
					answerNum = num.get(i);
					answerDeno = deno.get(i);
				}//End of if statement
				
			}//End of for loop
			
			//Deletion of all even indices in the tree list to make it run slightly faster
			for(int i= num.size() -1; i <= 0; i = i-2 )
			{
				num.remove(i);
				deno.remove(i);
			}
			
			
		}//End of while loop
		
		//Creation of file and writing of text file
		File outputFile = new File("output.txt");
		outputFile.createNewFile();
		
		//Creation of output Writer to write solution to a text document
		FileWriter outputWriter = new FileWriter(outputFile);
		outputWriter.write( answerNum.toString() );
		outputWriter.write(  System.getProperty( "line.separator" ));
		outputWriter.write( answerDeno.toString() );
		outputWriter.close();
		
		
		System.out.println("program finished");
		
	}//End of main runner
	
}//End of class
