public class MatrixChainOrder2 {
  private static int[] chain = {30, 35, 15, 5, 10, 20, 25};

  public static int recursive() {
    return recursive(1, chain.length - 1);
  }

  private static int recursive(int i, int j) {
    if (i == j)
      return 0;

    int res = Integer.MAX_VALUE;
    for (int k = i; k < j; k++) {
      int tmp = recursive(i, k) + recursive(k + 1, j) 
        + chain[i - 1] * chain[k] * chain[j];
      if (tmp < res)
        res = tmp;
    }

    return res;
  }

  public static int topDownDP() {
    int n = chain.length;
    int[][] s = new int[n][n];
    int[][] r = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++) {
        if (i == j)
          r[i][j] = 0;
        else
          r[i][j] = Integer.MIN_VALUE;
      }

    return topDownDPHelper(1, n - 1, r, s);
  }

  private static int topDownDPHelper(int i, int j, int[][] r, int[][] s) {
    if (r[i][j] >= 0)
      return r[i][j];

    int res = Integer.MAX_VALUE;
    for (int k = i; k < j; k++) {
      int tmp = topDownDPHelper(i, k, r, s) + topDownDPHelper(k + 1, j, r, s)
              + chain[i - 1] * chain[k] * chain[j];
      if (tmp < res) {
        res = tmp;
        r[i][j] = res;
        s[i][j] = k;
      }
    }
    if (i == 1 && j == r[0].length - 1) {
      MatrixChainOrder2.print(s);
      System.out.println();
    }

    return res;
  }

  public static void bottomUpDP() {
    int n = chain.length;

    int[][] r = new int[n][n];
    int[][] s = new int[n][n];
    
    for (int i = 0; i < n; i++)
      r[i][i] = 0;

    bottomUpDPHelper(r, s);
    System.out.println(r[1][n - 1]);
    print(s);
    System.out.println();
  }

  public static void bottomUpDPHelper(int[][] r, int[][] s) {
    int n = r[0].length;

    /* Visually, bottom-up approach can be seen as filling in a N * N matrix (r)
     * from the left-most column (r[][1]) to the rignt-most (r[][n - 1]). Each
     * iteration only considers the right-most column at the moment (r[][j]).
     * The tricky part is that jth column is filled in from right-most entry 
     * (r[j - 1][j], instead of from r[1][j - 1]). This would not look like a
     * bottom up approach, but it literally is, since for a given column, this 
     * filling-in scheme is actually starts from the shortest length, wrt j, to 
     * the farest entry. (r[j - 1][j] to r[j][1])
     * */

    for (int j = 2; j < n; j++) {
      //r[j - 1][j] = chain[j - 2] * chain[j - 1] * chain[j];
      for (int i = j - 1; i > 0; i--) {
        int res = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
          int tmp = r[i][k] + r[k + 1][j] + chain[i - 1] * chain[k] * chain[j];
          if (tmp < res) {
            res = tmp;
            r[i][j] = res;
            s[i][j] = k;
          }
        }
      }
    }

    return;
  }

  public static void print(int[][] s) {
    print(1, s[0].length - 1, s);
  }

  private static void print(int i, int j, int[][] s) {
    if (i == j) {
      System.out.print(i);
      return;
    }

    System.out.print("(");
    int k = s[i][j];
    print(i, k, s);
    print(k + 1, j, s);
    System.out.print(")");
    return;
  }

  public static void main(String[] args) {
    //int res = MatrixChainOrder2.recursive();
    int res = MatrixChainOrder2.topDownDP();

    System.out.println(res);
    MatrixChainOrder2.bottomUpDP();
  }
}
