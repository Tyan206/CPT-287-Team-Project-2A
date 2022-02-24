package infix_expression_parser;

import java.util.Scanner;

public class Program {
	
	public static void main(String[] args) {
		/*Main program where we will read in data from input file
		  and print output to console */

	}
	
	private static int precedence(String oper) {
        if (oper.equals("*") || oper.equals("/")) { return 12; }
        if (oper.equals("+") || oper.equals("-")) { return 11; }
        throw new IllegalArgumentException("Operator not supported");
    }
	
	public static String infixToPostfix(String infixExp) {
        Linked_List_Stack<String> stk = new Linked_List_Stack<>();
        StringBuilder postfix = new StringBuilder();
        Scanner scanner = new Scanner(infixExp);
        while (scanner.hasNext()) {
            String token = scanner.next();
            if (Character.isDigit(token.charAt(0))) { postfix.append(token).append(' '); }
            else if (token.equals("(")) { stk.push(token); }
            else if (token.equals(")")) {
                while (!stk.peek().equals("(")) { postfix.append(stk.pop()).append(' '); }
                stk.pop();
            } else {
                while (!stk.isEmpty() && !stk.peek().equals("(") && precedence(token) <= precedence(stk.peek())) {
                    postfix.append(stk.pop()).append(' ');
                }
                stk.push(token);
            }
        }
        while (!stk.isEmpty()) { postfix.append(stk.pop()).append(' '); }
        scanner.close();
        return postfix.toString();
    }
}
