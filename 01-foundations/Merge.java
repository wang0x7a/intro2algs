public class Merge {
   private static void merge(int[] a,  int lo, int mid, int hi) {
      // precondition
      assert isSorted(a, lo, mid);
      assert isSorted(a, mid + 1, hi);

      int[] left = new int[mid - lo + 2];
      int[] right = new int[hi - mid + 1];

      // Copy lo ... mid to 0 ... mid - lo
      for (int i = 0; i < mid - lo + 1; i++)
         left[i] = a[lo + i];

      // Copy mid + 1 ... hi to 0 ... hi - mid
      for (int i = 0; i < hi - mid; i++)
         right[i] = a[mid + i + 1];

      // sentinel
      left[mid - lo + 1] = Integer.MAX_VALUE;
      right[hi - mid] = Integer.MAX_VALUE;

      // merge back to a[]
      int i = 0, j = 0;
      for (int k = lo; k <= hi; k++) {
         if (left[i] <= right[j]) a[k] = left[i++];
         else a[k] = right[j++];
      }

      // postcondition
      assert isSorted(a, lo, hi);
   }

   public static void sort(int[] a) {
      sort(a, 0, a.length - 1);
      assert isSorted(a);
   }

   private static void sort(int[] a, int lo, int hi) {
      if (hi <= lo) return;
      int mid = (hi + lo) / 2;
      sort(a, lo, mid);
      sort(a, mid + 1, hi);
      merge(a, lo, mid, hi);
   }

   private static boolean isSorted(int[] a) {
      return isSorted(a, 0, a.length - 1);
   }

   private static boolean isSorted(int[] a, int lo, int hi) {
      for (int i = lo + 1; i <= hi; i++)
         if (a[i] < a[i - 1]) return false;
      return true;
   }

   public static void main(String[] args) {
      int[] a = {5, 2, 4, 7, 1, 3, 2, 6};
      Merge.sort(a);

      for (int i = 0; i < a.length; i++)
         System.out.print(a[i] + " ");

      System.out.println();
   }
}
