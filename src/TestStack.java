import adt.Stack;
import dataStructures.LinkedStack;
import dataStructures.StackList;

public class TestStack {

	public static void main(String[] args) {
		
		System.out.println("Testing LinkedStack");
		Stack<Integer> st = new LinkedStack<>();
		
		st.push(3);
		st.push(4);
		st.push(1);
		st.push(7);
		st.pop();
		st.push(9);
		st.pop();
		st.pop();
		st.push(5);
		st.push(6);

		while (!st.isEmpty()) {
			System.out.println(st.peek());
			st.pop();
		}
		
		System.out.println("\nTesting StackList");
		st = new StackList<>();
		
		st.push(3);
		st.push(4);
		st.push(1);
		st.push(7);
		st.pop();
		st.push(9);
		st.pop();
		st.pop();
		st.push(5);
		st.push(6);

		while (!st.isEmpty()) {
			System.out.println(st.peek());
			st.pop();
		}
	}

}
