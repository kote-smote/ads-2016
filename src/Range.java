import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Dadena e ravenkata: x2+s(x)+200·x = N kade sto x, N se prirodni broevi, 
 * a s(x) e funkcija koja go dava zbirot na cifri na brojot x. 
 * Daden e i brojot N i dva prirodni broevi A i B, 
 * kade sto A <= B i A, B <= 1,000,000,000. 
 * Potrebno e da proverite dali postoi priroden broj x vo opsegot [A, B] 
 * taka sto e zadovolena racenkata, i ako postoi togas treba da se vrati
 * kako rezultat. Ako takov priroden broj x ne postoi, togas se vrakja -1.
 */
public class Range {
	
	private static PrintWriter writer;

	public static void main(String[] args) throws Exception {
		 writer = new PrintWriter("test_input/Range/output.txt");
		 
		 for (int i = 1; i <= 10; i++) {
			 writer.println("===TEST" + i + "===");
			 test("test_input/Range/input_" + i + ".txt");
			 writer.println();
		 }
		 
		 writer.close();
		 
	}
    
    public static void test(String fileName) throws Exception {
        
        BufferedReader br = new BufferedReader(new FileReader(fileName));
       
        
        long N = Long.parseLong(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        
        long res = check(N, A, B);
        writer.println(res);
       
        br.close();
        
    }
    
    public static int sumOfDigits(long n) {
		long tmp = n;
		int sum = 0;
		while (tmp != 0) {
			sum += tmp % 10;
			tmp /= 10;
		}
		return sum;
	}
    
    public static long estimate(long x) {
    	return x * x + sumOfDigits(x) + 200*x;
    }
    
    static long check(long N, long A, long B) {
     	long res = (A + B) / 2;
        if (estimate(res) == N) return res;
        if (A == B) return -1;
        if (estimate(res) > N) 
            return check(N, A, res);
        else 
            return check(N, res + 1, B);
    }
    
}
