import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import adt.Stack;
import dataStructures.StackList;

public class ExpressionEvaluatorBasic {

	public static int evaluate(String expression){
		Stack<Integer> op = new StackList<>(); // operands stack
		Scanner scanOperands = new Scanner(expression);
		Scanner scanOperators = new Scanner(expression);
		scanOperands.useDelimiter("[*,+]");
		scanOperators.useDelimiter("[0-9]{1,}");
        boolean multiply = false;
        int result = 0;
		
		while (scanOperands.hasNext()) {
			op.push(scanOperands.nextInt());
            // If the previous operator was '*'
			if (multiply) 
				op.push(new Integer(op.pop() * op.pop()));
  
            multiply = (scanOperators.hasNext() 
            		&& !scanOperators.next().equals("*")) ? 
            		false : true;
		}
		/* The operands stack is left with only the operands 
		   that should be added with each other */
		while (!op.isEmpty()) 
			result += op.pop();
        
		scanOperands.close();
		scanOperators.close();
		return result;
    }
    
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		System.out.println(evaluate(input.readLine()));
	}

}