import java.util.Scanner;

import adt.Stack;
import dataStructures.StackList;

class DivisionByZeroException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DivisionByZeroException() {
		super();
	}
	
	public DivisionByZeroException(String msg) {
		super(msg);
	}
}

public class PostfixExpressionEvaluator {
	
	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		System.out.println("Enter arithmetic expression in postfix notation:");
		// try for input: 5 9 + 2 * 6 5 * +
		String expression = scanner.nextLine();
		System.out.println(evaluate(expression));
	}
	
	public static int evaluate(String expression) {
		Stack<String> stack = new StackList<>();
		String[] symbols = expression.split(" ");
		
		for (String symbol : symbols) {
			
            if (symbol.matches("[0-9]{1,}"))
				stack.push(symbol);
			
			else if (symbol.matches("[*,+,\\-,/]")) {
                int operand2 = Integer.parseInt(stack.pop());
				int operand1 = Integer.parseInt(stack.pop());
				
				switch(symbol) {
					case "+": stack.push(new Integer(operand1 + operand2).toString());
							  break;
					case "*": stack.push(new Integer(operand1 * operand2).toString());
						      break;
					case "-": stack.push(new Integer(operand1 - operand2).toString());
				      		  break;
					case "/": { if (operand2 == 0)
									throw new RuntimeException();
								stack.push(new Integer(operand1 / operand2).toString());
				      	      	break;
				      	      }
				}
			}
		}
		
		return Integer.parseInt(stack.pop());
	}
}
