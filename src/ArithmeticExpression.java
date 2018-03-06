import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Labaratoriska Vezba 3 - Zadaca 1:
 * Daden e aritmetici izraz bo oblik (A+B) ili (A-B) kade sto A i B istovremeno se 
 * drugi aritmeticki izrazi ili istovremeno se cifri od 0-9. 
 * Treba da se evaluira dadeniot izraz.
 */
public class ArithmeticExpression {
	
	private static PrintWriter writer;

	public static void main(String[] args) throws Exception {
		 
		writer = new PrintWriter("test_input/ArithmeticExpression/output.txt");

		for (int i = 1; i <= 10; i++) {
			writer.println("===TEST" + i + "===");
			test("test_input/ArithmeticExpression/input_" + i + ".txt");
			writer.println();
		}
		 
		writer.close();
	}
	
	public static void test(String fileName) throws Exception {
                
		BufferedReader br = new BufferedReader(new FileReader(fileName));
        
        String expression = br.readLine();
        char exp[] = expression.toCharArray();
        
        int res = estimate(exp, 0, exp.length-1);
        writer.println(res);
        
        br.close();
        
    }
    
	// funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks rr
    static int estimate(char c[], int l, int r) {
        // Base case:
        if (c.length == 5) {
            int operand1 = Character.getNumericValue(c[1]);
            int operand2 = Character.getNumericValue(c[3]);
            if (c[2] == '+')
                return operand1 + operand2;
            else if (c[2] == '-')
                return operand1 - operand2;
        }
        
        // Divide the expression into two separate groups
        char[] gr1 = new char[groupLength(c)];
        System.arraycopy(c, 1, gr1, 0, groupLength(c));
        
        char[] gr2 = new char[c.length - gr1.length - 3]; // -3 means: minus [ "(" , ")" , "+ or -" ]
        System.arraycopy(c, gr1.length+2, gr2, 0, c.length - gr1.length - 3);
        
        // Conquer
        if (c[groupLength(c) + 1] == '+')
            return estimate(gr1, 0, gr1.length-1) + estimate(gr2, 0, gr2.length - 1);
        
        else if (c[groupLength(c) + 1] == '-')
            return estimate(gr1, 0, gr1.length-1) - estimate(gr2, 0, gr2.length - 1);
            
        else throw new UnsupportedOperationException();
    }
    
    // Given an expression as char array, return the 
    // length of the first sub expression
    public static int groupLength(char[] c) {
        int i = 1; // start at index 1 - don't count the main '('
        int flag = 0;
        while (i != c.length) {
            if (c[i] == '(') 
                flag++;
            else if (c[i] == ')')
                flag--;
            if (flag == 0)
            	break;
            i++;
        }
        return i;
    }
}
