package binary;

public class TheorySearch {
    public static void main(String[] args) {
        int test[] = new int[]{-10, 1, 3, 5, 7}; // 3
        System.out.println(search(test));
    }

    private static int search(int[] arr) {
        int begin = 0, end = arr.length - 1;

        while (begin <= end) {
            int middleIndex = (end + begin) / 2;
            if (middleIndex == arr[middleIndex]) return middleIndex;
            else if (middleIndex > arr[middleIndex]) begin = middleIndex + 1;
            else end = middleIndex - 1;
        }
        return -1;
    }

}
