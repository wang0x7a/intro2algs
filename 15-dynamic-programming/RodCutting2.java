public class RodCutting {
  public static int[] prices = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

  public static int recursive(int n) {
    if (n < 0)
      return 0;

    int res = prices[n];
    int tmp;
    for (int i = 1; i <= n; i++) {
      tmp = recursive(n - i) + prices[i];
      if (tmp > res)
        res = tmp;
    }

    return res;
  }

  public static int topDownDP(int n) {
    int[] record = new int[n + 1];
    for (int i = 0; i < n + 1; i++)
      record[i] = Integer.MIN_VALUE;
    record[0] = 0;

    return topDownDPHelper(n, record);
  }

  private static int topDownDPHelper(int n, int[] record) {
    if (record[n] >= 0)
      return record[n];

    int res = prices[n];
    // We initializa tmp as negative infinite, ensuring that every of prices
    // array is touched??
    int tmp = Integer.MIN_VALUE;
    // normal computation if we did not compute record[n]
    for (int i = 1; i <= n; i++) {
      tmp = topDownDPHelper(n - i, record) + prices[i];
      if (tmp > res)
        res = tmp;
    }

    record[n] = res;

    return res;
  }

  public static int bottomUpDP(int n) {
    int[] record = new int[n + 1];
    record[0] = 0;

    for (int i = 1; i <= n; i++) {
      int res = prices[i];
      int tmp = Integer.MIN_VALUE;
      for (int j = 1; j < i; j++ ) {
        tmp = prices[j] + record[i - j];
        if (tmp > res)
          res = tmp;
      }
      record[i] = res;
    }
    return record[n];
  }

  public static void bottomUpDPEx(int n, int[] r, int[] s) {
    for (int i = 1; i <= n; i++) {
      // unlike bottomUpDP, we cannot initialize res as prices[i] here,
      // since length i should also be taken into consideration in the 
      // inner loop.
      int res = Integer.MIN_VALUE;
      int tmp;
      for (int j = 1; j <= i; j++) {
        tmp = prices[j] + r[i - j];
        if (tmp > res) {
          res = tmp;
          s[i] = j;
        }
      }
      r[i] = res;
    }

    return;
  }

  public static void printSol(int n, int[] s) {
    while (n > 0) {
      System.out.print(s[n] + " ");
      n = n - s[n];
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);

    int rec = RodCutting.recursive(n);
    System.out.println("Recursive: " + rec);

    int topDown = RodCutting.topDownDP(n);
    System.out.println("Tod-down with memoization: " + topDown);

    int bottomUp = RodCutting.bottomUpDP(n);
    System.out.println("Bottom-up: " + bottomUp);

    int[] r = new int[n + 1];
    int[] s = new int[n + 1];
    r[0] = 0;
    s[0] = 0;
    RodCutting.bottomUpDPEx(n, r, s);
    System.out.println("Bottom-up Ex: " + r[n]);
    RodCutting.printSol(n, s);
  }
}
