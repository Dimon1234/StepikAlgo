package merge;

import java.util.Scanner;

public class First {
    private static long count = 0;
    private static long[] temp;
    private static long[] a;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        a = new long[n];
        temp = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        mergeSort(0, n);
        System.out.println(count);
    }

    private static void mergeSort(int l, int r) {
        if (r <= l + 1) return;
        int m = (l + r) >> 1; // (l + r)/2
        mergeSort(l, m);
        mergeSort(m, r);
        merge(l, m, r);
    }

    private static void merge(int l, int m, int r) {
        int i = l;
        int j = m;
        for (int k = l; k < r; k++) {
            if (j == r || (i < m && a[i] <= a[j])) {
                temp[k] = a[i];
                i++;
            } else {
                temp[k] = a[j];
                count += m - i;
                j++;
            }
        }
        System.arraycopy(temp, l, a, l, r - l);
    }
}
