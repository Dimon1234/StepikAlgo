package greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CountSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) arr[i] = scanner.nextInt();
        StringBuilder result = new StringBuilder();
        IntStream.of(arr)
                .collect(HashMap<Integer, Integer>::new, ((hashMap, value) -> {
                    if (hashMap.containsKey(value)) hashMap.put(value, hashMap.get(value) + 1);
                    else hashMap.put(value, 1);
                }), HashMap::putAll)
                .entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .forEach(entry -> {
                    for (int i = 0; i < entry.getValue(); i++) result.append(entry.getKey()).append(" ");
                });
        System.out.println(result.toString().trim());
    }
}
