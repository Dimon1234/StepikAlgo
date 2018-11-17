package huffman;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Second {
    private static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine().split(" ")[0]);
        Queue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split(": ");
            queue.add(new Node(data[0], data[1], null, null));
        }
        String[] codes = scanner.nextLine().split("");
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            queue.add(new Node(null, left.code.substring(0, left.code.length() - 1), left, right));
        }
        Node root = queue.poll();
        for (String s : codes) {
            builder.append(getC(root, s));
        }
        System.out.println(builder.toString());
    }

    private static String getC(Node node, String code) {
        if (code.equals(node.getLeft().getCode())) return node.getLeft().getC();
        else if (code.equals(node.getRight().getCode())) return node.getRight().getC();
        if (code.startsWith(node.getLeft().getCode())) return getC(node.getLeft(), code);
        else return getC(node.getRight(), code);
    }

    private static class Node implements Comparable<Node> {
        private String c;
        private String code;
        private Node left;
        private Node right;

        public Node(String c, String code, Node left, Node right) {
            this.c = c;
            this.code = code;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "c='" + c + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return -Integer.compare(this.code.length(), o.code.length());
        }
    }
}
