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
      int tmp = recursive(i, k) + recursive(k + 1, j) + chain[i - 1] * chain[k] * chain[j];
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
  }

  public static void bottomUpDPHelper(int[][] r, int[][] s) {
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
  }
}
