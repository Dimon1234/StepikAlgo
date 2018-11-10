package greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Second {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int W = scanner.nextInt();
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            items.add(new Item(scanner.nextInt(), scanner.nextInt()));
        }
        System.out.println(new Second().cost(items, W));
    }

    private String cost(List<Item> items, int W) {
        items.sort(Comparator.comparingDouble(o -> -(double) o.getCost() / o.getVolume()));

        double count = 0;
        for (Item item : items) {
            if (W >= item.getVolume()) {
                W -= item.getVolume();
                count += item.getCost();
            } else {
                count += W * ((double) item.getCost() / item.getVolume());
                break;
            }
        }
        return String.format("%.3f", count);
    }

    private static class Item {
        private int cost;
        private int volume;

        public Item(int cost, int volume) {
            this.cost = cost;
            this.volume = volume;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }
    }
}
