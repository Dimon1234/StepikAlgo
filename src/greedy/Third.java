package greedy;

import java.util.Scanner;

/**
 * Задача на программирование: различные слагаемые
 * <p>
 * По данному числу 1≤n≤109 найдите максимальное число k, для которого n можно представить как сумму k
 * различных натуральных слагаемых. Выведите в первой строке число k, во второй — k слагаемых.
 */
public class Third {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        System.out.println(new Third().count(a));
    }

    private String count(long a) {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        int count = 0;
        while (a != 0) {
            if ((a - i) > i || a - i == 0) {
                a -= i;
                builder.append(i).append(" ");
                count++;
            }
            i++;
        }
        return String.valueOf(count) + "\n" + builder.toString().trim();
    }
}
