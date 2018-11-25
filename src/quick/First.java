package quick;

import java.util.Arrays;
import java.util.Scanner;

public class First {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        long[] L = new long[n];
        long[] R = new long[n];
        for (int i = 0; i < n; i++) {
            L[i] = scanner.nextLong();
            R[i] = scanner.nextLong();
        }
        Arrays.sort(L);
        Arrays.sort(R);
        long[] arr = new long[k];
        for (int i = 0; i < k; i++) arr[i] = scanner.nextLong();

        count(L, R, arr, n);
    }

    private static void count(long[] L, long[] R, long[] k, int n) {
        StringBuilder result = new StringBuilder();
        long cnt;
        for (int i = 0; i < k.length; ++i) {
            //сколько отрезков начинается левее х
            int left = 0, right = n - 1, m;
            while (left <= right) {
                m = (left + right) / 2; //середина
                if (L[m] > k[i])
                    right = m - 1;   //справа отрезки правее точки
                else
                    left = m + 1;
            }
            cnt = right + 1;
            //сколько отрезков кончается правее х
            left = 0;
            right = n - 1;
            while (left <= right) {
                m = (left + right) / 2; //середина
                if (R[m] < k[i])
                    left = m + 1;   //слева отрезки закончились раньше x
                else
                    right = m - 1;
            }
            cnt -= right + 1;
            result.append(cnt).append(" ");
        }
        System.out.println(result.toString().trim());
    }
}
