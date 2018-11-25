package binary;

import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder result = new StringBuilder();
        int n = scanner.nextInt();
        int arrn[] = new int[n];
        for (int i = 0; i < n; i++) arrn[i] = scanner.nextInt();
        int k = scanner.nextInt();
        for (int i = 0; i < k; i++) {
            result.append(binarySearch(arrn, scanner.nextInt())).append(" ");
        }
        System.out.println(result.toString().trim());
    }

    private static int binarySearch(int[] arrn, int k) {
        int beginIndex = 0;
        int endIndex = arrn.length - 1;

        while (beginIndex <= endIndex) {
            int middleIndex = (endIndex + beginIndex) / 2;
            if (k == arrn[middleIndex]) return middleIndex + 1;
            else if (k > arrn[middleIndex]) beginIndex = middleIndex + 1;
            else endIndex = middleIndex - 1;
        }
        return -1;
    }
}
