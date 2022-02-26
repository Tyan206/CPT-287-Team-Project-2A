package infix_expression_parser;

import java.util.Scanner;

public class Infix_Parser {
	
	/** Tests whether parentheses are balanced in an expression.
		@param exp: expression to test
	    @return: {true} if parentheses are balanced in the expression; {false} otherwise
	*/
	public static boolean isBalanced(String exp) {
	    Linked_List_Stack<Character> stk = new Linked_List_Stack<>();
	    for (int i = 0; i < exp.length(); i++) {
	        if (exp.charAt(i) == '(' || exp.charAt(i) == '[' || exp.charAt(i) == '{') { stk.push(exp.charAt(i)); }
	        if (exp.charAt(i) == ')' || exp.charAt(i) == ']' || exp.charAt(i) == '}') {
	            if (stk.isEmpty()) { return false; }
	            if (exp.charAt(i) == ')' && stk.peek() != '(') { return false; }
	            if (exp.charAt(i) == ']' && stk.peek() != '[') { return false; }
	            if (exp.charAt(i) == '}' && stk.peek() != '{') { return false; }
	            stk.pop();
	        }
	    }
	    return stk.isEmpty();
	}
	
	/** Evaluates a postfix expression using a stack.
	    @param postfixExp: postfix expression to evaluate
	    @return: evaluation result
	*/
	public static int evaluate(String postfixExp) {
	    Linked_List_Stack<Integer> stk = new Linked_List_Stack<>();
	    Scanner scanner = new Scanner(postfixExp);
	    while (scanner.hasNext()) {
	        String token = scanner.next();
	        if (Character.isDigit(token.charAt(0))) { stk.push(Integer.valueOf(token)); }
	        else {
	            int rightOperand = stk.pop(), leftOperand = stk.pop();
	            // Supported operators
	            if (token.equals("+")) { stk.push(leftOperand + rightOperand); }
	            if (token.equals("-")) { stk.push(leftOperand - rightOperand); }
	            if (token.equals("*")) { stk.push(leftOperand * rightOperand); }
	            if (token.equals("/")) {
	                if (rightOperand == 0) {
	                    scanner.close();
	                    throw new ArithmeticException("Dividing by zero");
	                }
	                stk.push(leftOperand / rightOperand);
	            }
	        }
	    }
	    scanner.close();
	    return stk.pop();
	}
	
	/** Returns the precedence of an operator.
	    @param oper: operator to find its precedence
	    @return: precedence of the operator
	    @throws IllegalArgumentException: operator is not supported.
	*/
	private static int precedence(String oper) {
	    if (oper.equals("||")){ return 1; }
	    if (oper.equals("&&")){ return 2; }
	    if (oper.equals("==") || (oper.equals("!="))){ return 3; }
	    if (oper.equals(">") || (oper.equals(">=")) || (oper.equals("<")) || (oper.equals("<="))){ return 4; }
	    if (oper.equals("+") || (oper.equals("-"))){ return 5; }
	    if (oper.equals("*") || (oper.equals("/")) || (oper.equals("%"))){ return 6; }
	    if (oper.equals("^")){ return 7; }
	    throw new IllegalArgumentException("Operator not supported");
	}
	
	/** Converts an infix expression to postfix expression.
	    @param infixExp: infix expression to convert
	    @return: result postfix expression
	*/
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
