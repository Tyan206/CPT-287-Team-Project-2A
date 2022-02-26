package infix_expression_parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Program {
	
	public static void main(String[] args) throws IOException {
		/*Main program where we will read in data from input file
		  and print output to console */
		FileInputStream inputfile = new FileInputStream("input.txt");
		Scanner scanner = new Scanner(inputfile);
		Infix_Parser parser = new Infix_Parser();
		String exp = "";
		while(scanner.hasNext()) {
			exp += scanner.next(); // this will skip space and \t
			//String exp = scanner.nextLine().replaceAll(" ", "");
		}
		parser.evaluate(exp);
		
		inputfile.close();
		scanner.close();
	}//end main
}//end Program
