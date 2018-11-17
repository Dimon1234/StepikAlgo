package priority;

import java.util.PriorityQueue;
import java.util.Scanner;

public class First {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        StringBuilder result = new StringBuilder();

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> -Long.compare(o1, o2));

        for (int i = 0; i < N; i++) {
            String command = scanner.nextLine();
            if (command.startsWith("Insert")) queue.add(Integer.valueOf(command.substring(command.indexOf(' ') + 1)));
            if (command.equals("ExtractMax")) result.append(queue.poll()).append("\n");
        }
        System.out.println(result.toString().trim());
    }
}

