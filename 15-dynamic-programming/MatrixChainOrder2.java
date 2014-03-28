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
    int[][] r = new int[n + 1][n + 1];
    for (int i = 0; i <= n; i++)
      for (int j = 0; j <= n; j++) {
        if (i == j)
          r[i][j] = 0;
        else
          r[i][j] = Integer.MIN_VALUE;
      }

    return topDownDPHelper(1, n - 1, r);
  }

  private static int topDownDPHelper(int i, int j, int[][] r) {
    if (r[i][j] >= 0)
      return r[i][j];

    int res = Integer.MAX_VALUE;
    for (int k = i; k < j; k++) {
      int tmp = topDownDPHelper(i, k, r) + topDownDPHelper(k + 1, j, r)
              + chain[i - 1] * chain[k] * chain[j];
      if (tmp < res) {
        res = tmp;
        r[i][j] = res;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    //int res = MatrixChainOrder2.recursive();
    int res = MatrixChainOrder2.topDownDP();

    System.out.println(res);
  }
}
