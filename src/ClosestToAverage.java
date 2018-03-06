import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Algoritmi i Podatocni Strukturi - Lab  01 - Zadaca 1
 * Za dadena niza od N (1≤N≤50) prirodni broevi, da se najde brojot 
 * koj e najblisku do nivniot prosek. Ako postojat dva broja so isto 
 * rastojanie do prosekot, da se vrati pomaliot od niv. 
 * 
 * Primer ako nizata e:
 * 1, 2, 3, 4, 5 
 * prosekot e (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3, 
 * sto znaci deka brojot koj treba da se vrati i e najblisku 
 * do prosekot e 3
 * 
 * Za nizata 1, 2, 3, 4, 5, 6 prosekot e 3.5 i dvata broja 3 i 4 se 
 * na isto rastojanie od prosekot. Treba da se vrati pomaliot od niv,
 * t.e brojot 3;
 * 
 * Prviot broj od vlezot e brojot na elementi vo nizata N, 
 * a potoa vo sekoj red se dadeni broevite.
 * 
 * Zabeleska:
 * Da se kreira podatocna struktura niza i istata da se iskoristi 
 * vo zadacata.
 */
public class ClosestToAverage {

	public static int min(int a, int b) {
		return a < b ? a : b;
	}
	
	public static double average(ArrayList<? extends Number> arr) {
		double result = 0;
		for (Number n : arr)
			result += n.doubleValue();
		return result /= arr.size();
	}

	public static int find(ArrayList<Integer> arr) {
		double average = average(arr);

		int res = arr.get(0);
		double diff = Math.abs(arr.get(0).intValue() - average);

		for (int i = 1; i < arr.size(); i++) {
			double currDiff = Math.abs(arr.get(i) - average);
			int currRes = arr.get(i);

			if (currDiff < diff) {
				diff = currDiff;
				res = currRes;
			}

			if (currDiff == diff)
				res = min(res, currRes);
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
		ArrayList<Integer> array = new ArrayList<Integer>();

		for (int i = 0; i < N; i++)
			array.add(i, Integer.parseInt(stdin.readLine()));

		System.out.println(find(array));
	}
}

/*

TEST EXAMPLES:
 
TEST 1:
Input:
5
1
2
3
4
5
Output: 3
 
TEST 2:
Input:
2
3
1
Output:
1

TEST 3:
Input:
1
1
Output:
1

TEST 4:
Input:
19
9
72
18
97
43
68
63
82
89
7
18
2
66
31
46
56
57
96
88
Output: 56
*/
