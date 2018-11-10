package greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Задача на программирование: покрыть отрезки точками
 * <p>
 * <p>
 * <p>
 * По данным n
 * отрезкам необходимо найти множество точек минимального размера, для которого каждый из отрезков содержит хотя бы одну из точек.
 * <p>
 * В первой строке дано число 1≤n≤100 отрезков. Каждая из последующих n строк содержит по два числа 0≤l≤r≤109,
 * задающих начало и конец отрезка. Выведите оптимальное число m точек и сами m точек. Если таких множеств точек несколько,
 * выведите любое из них.
 */
public class First {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        List<AB> abs = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            abs.add(new AB(scanner.nextLong(), scanner.nextLong()));
        }
        System.out.println(new First().count(abs));

    }

    private String count(List<AB> abList) {
        StringBuilder result = new StringBuilder();
        abList.sort(Comparator.comparingLong(o -> o.b));
        int i = 1;
        long last = abList.get(0).getB();
        result.append(last).append(" ");
        for (int j = 1; j < abList.size(); j++) {
            if (!abList.get(j).contains(last)) {
                result.append(abList.get(j).getB()).append(" ");
                last = abList.get(j).getB();
                i++;
            }
        }
        return String.valueOf(i) + "\n" + result.toString().trim();
    }

    private static class AB {
        private long a;
        private long b;

        public AB(long a, long b) {
            this.a = a;
            this.b = b;
        }

        public boolean contains(long c) {
            return a <= c && b >= c;
        }

        public long getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public long getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }
}
