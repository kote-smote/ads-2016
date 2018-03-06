import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import adt.Stack;
import dataStructures.StackList;

public class ExpressionEvaluator {

	public static int evaluateExpression(String expression){
		// TODO impelemt the method to support (-) and (/) operations as well
		Stack<String> operators = new StackList<>();
		Stack<Integer> operands = new StackList<>();
		Scanner scanOperands = new Scanner(expression);
		Scanner scanOperators = new Scanner(expression);
		scanOperands.useDelimiter("[*,+]");
		scanOperators.useDelimiter("[0-9]{1,}");
		int res = 0;
        String operator;
		
		while (scanOperands.hasNext()) {
			operands.push(scanOperands.nextInt());
			// Check if the previous operator was '*'
			if (!operators.isEmpty()&&operators.peek().equals("*")) {
				operators.pop(); // pop the '*' value from the operators stack
                int op1 = operands.pop();
				int op2 = operands.pop();
				operands.push(new Integer(op1 * op2));
			}
			// only if there are still operators in the expression, push them
            if (scanOperators.hasNext() && (operator = scanOperators.next()).equals("*"))
                operators.push(operator);
		}
		/* The operands stack is left with only the operands 
		   that should be added with each other */
		while (!operands.isEmpty()) 
			res += operands.pop();
        
		scanOperands.close();
		scanOperators.close();
		return res;
    }
    
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		System.out.println(evaluateExpression(input.readLine()));
	}

}