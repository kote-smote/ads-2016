public class Knapsack {

	public static int max(int a, int b) { return (a > b) ? a : b; }
	
	/* A Dynamic Programming based solution for 0-1 Knapsack problem. */
	// Returns the maximum value that can be put in a knapsack of capacity C
	public static int knapSack(int C, int wt[], int val[], int n) {
		int K[][] = new int[n+1][C+1];
		
		// Built table K[][] in bottom up manner
		for (int i = 0; i <= n; i++) {
			for (int w = 0; w <= C; w++) {
				if (i == 0 || w == 0)
					K[i][w] = 0;
				else if (wt[i-1] <= w)
					K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]], K[i-1][w]);
				else 
					K[i][w] = K[i-1][w];
			}
		}
		
		return K[n][C];
	}
     
	/* A Naive recursive implementation of 0-1 Knapsack problem */
    // Returns the maximum value that can be put in a knapsack of capacity W
    static int knapSackRecursive(int W, int wt[], int val[], int n) {
        // Base Case
    	if (n == 0 || W == 0)
    		return 0;
     
        // If weight of the nth item is more than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
    	if (wt[n-1] > W)
    		return knapSack(W, wt, val, n-1);
     
    	// Return the maximum of two cases: 
    	// (1) nth item included 
    	// (2) not included
    	else return max( val[n-1] + knapSack(W-wt[n-1], wt, val, n-1), 
    					knapSack(W, wt, val, n-1));
     }
	
	
	public static void main(String[] args) {
		
		int val[] = new int[]{120, 60, 100};
	    int wt[] = new int[]{30, 10, 20};
	    int  C = 50;
	    int n = val.length;
	    System.out.println(knapSack(C, wt, val, n));
//	    System.out.println(knapSackRecursive(C, wt, val, n));
	    
	}	
}
