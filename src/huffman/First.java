package huffman;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class First {
    private static Map<String, String> codesMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Map<String, Integer> hashMap = Stream.of(str.split("")).collect(HashMap::new,
                (map, s) -> {
                    if (!map.containsKey(s)) map.put(s, 1);
                    else map.put(s, map.get(s) + 1);
                }, HashMap::putAll);
        Queue<Node> queue = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue(), null, null));
        }
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            queue.add(new Node(left.getC() + right.getC(), left.getSum() + right.getSum(), left, right));
        }

        Node root = queue.poll();
        createCodesMap(root, "");

        StringBuilder builder = new StringBuilder();
        for (String s : Stream.of(str.split("")).collect(Collectors.toList())) {
            builder.append(codesMap.get(s));
        }
        System.out.println(hashMap.keySet().size() + " " + builder.toString().length());
        for (Map.Entry<String, String> entry : codesMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println(builder.toString());
    }

    private static void createCodesMap(Node node, String code) {
        if (node.isLeaf()) {
            if (code.equals("")) code = "0";
            codesMap.put(node.getC(), code);
        } else {
            if (node.getLeftNode() != null) createCodesMap(node.getLeftNode(), code + "0");
            if (node.getRightNode() != null) createCodesMap(node.getRightNode(), code + "1");
        }
    }

    private static class Node implements Comparable<Node> {
        private String c;
        private int sum;
        private Node leftNode;
        private Node rightNode;

        public Node(String c, int sum, Node leftNode, Node rightNode) {
            this.c = c;
            this.sum = sum;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public boolean isLeaf() {
            return this.leftNode == null && this.rightNode == null;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.sum, o.sum);
        }
    }
}
