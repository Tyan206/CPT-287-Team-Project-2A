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
			exp += scanner.nextLine().replace(" ", "").replace("\t", ""); // this will skip space and \t
			parser = new Infix_parser(exp);
			System.out.println(parser.toString());
		}
		inputfile.close();
		scanner.close();
	}//end main
}//end Program
