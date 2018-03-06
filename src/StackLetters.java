import java.util.Scanner;

import adt.Stack;
import dataStructures.LinkedStack;

public class StackLetters {
    
    public static void main(String[] args) {
        char [] arr = new char[100];

        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        arr = str.toCharArray();

        boolean res = checkEqualNumberOfAppearances(arr);
        System.out.println(res);
        
        scanner.close();
    }
    
    
    static boolean checkEqualNumberOfAppearances(char[] arr) {
        Stack<Character> st = new LinkedStack<Character>();
       
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == 'S' || arr[i] == 'T')
                st.push(arr[i]);

        // get the number of 'T' appearances after the last 'S'
        int afterLast = 0;
        while (st.pop() != 'S')
            afterLast++;

        // compare afterLast with 'T' appearances after every 'S'
        // if one fails, return 0
        int appearances = 0;
        while (!st.isEmpty()) {
            if (st.peek() == 'T') {
                ++appearances;
                st.pop();
            } else {
                if (appearances != afterLast)
                    return false;
                st.pop();
                appearances = 0; // reset for every 'S'
            }
        }
        // 'T' appearances after every 'S' were same, return 1
        return true; 
    }
}
