public class MatrixChainOrder {
  private static int[] chain = {30, 35, 15, 5, 10, 20, 25};

  public static int[][][] getOptimalOrder(int[] chain) {
    if (chain == null) chain = MatrixChainOrder.chain;

    int n = chain.length;

    int[][] m = new int[n][n];
    int[][] s = new int[n][n];

    for (int i = 0; i < n; i++) m[i][i] = 0;

    // l is the size of a subproblem 
    for (int l = 2; l < n; l++) {
      // i is the start index of a subproblem
      for (int i = 1; i < n - l + 1; i++) {
        // j is the end index of a subproblem
        int j = i + l - 1;
        
        // initialize each cell of m as infinity, so that each subproblem 
        // must be solved once.
        m[i][j] = Integer.MAX_VALUE; 

        for (int k = i; k < j; k++) {
          int tmp = m[i][k] + m[k + 1][j] + chain[i - 1] * chain[k] * chain[j];

          if (tmp < m[i][j]) {
            m[i][j] = tmp;
            s[i][j] = k;
          }
        }
      }
    }

    int[][][] res = new int[2][n][n];
    res[0] = m;
    res[1] = s;
    return res;
  }

  private static void printOptimalOrder(int[][][] r, int i, int j) {
    if (i == j) return; 
    else {
      int k = r[1][i][j];
      System.out.print(k + " ");

      printOptimalOrder(r, i, k);
      printOptimalOrder(r, k + 1, j);
    }
  }

  private static void printOptimalOrderX(int[][][] r, int i, int j) {
    if (i == j) System.out.print(i); 
    else {
      int k = r[1][i][j];
      System.out.print("(");

      printOptimalOrderX(r, i, k);
      printOptimalOrderX(r, k + 1, j);
      
      System.out.print(")");
    }
  }

  public static void printOptimalOrder(int[][][] r) {
    int n = r[0][0].length;
    //printOptimalOrder(r, 1, n - 1);
    printOptimalOrderX(r, 1, n - 1);
    System.out.println();
  }

  public static void main(String[] args) {
    int[][][] optimalOrder = getOptimalOrder(chain);

    printOptimalOrder(optimalOrder);
  }
}
