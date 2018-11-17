package huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Second {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine().split(" ")[0]);
        Map<String, String> codesMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split(": ");
            codesMap.put(data[1], data[0]);
        }
        String[] codes = scanner.nextLine().split("");
        StringBuilder buffer = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for (String code : codes) {
            String c = codesMap.get(buffer + code);
            if (c == null) {
                buffer.append(code);
                continue;
            }
            result.append(c);
            buffer = new StringBuilder();
        }
        System.out.println(result.toString());
    }
}
