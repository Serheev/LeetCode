package com.serheev.solutions.greedy_algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Greedy Knapsack
 * The first line contains the number of items 1 <= n <= 10^3 and the capacity of the backpack 0 <= W <= 2*10^6.
 * Each of the following n lines sets the cost 0 <= c_i <= 2*10^6 and the volume 0 < w_i <= 2*10^6 of the item
 * (n, W, c_i, w_i are integers). Output the maximum cost of parts of items (you can separate any part
 * from each item, the cost and volume will decrease proportionally) that fit into this backpack, with an accuracy of
 * at least three decimal places.
 */
public class GreedyKnapsack {

    public static void main(String[] args) throws FileNotFoundException {
        new GreedyKnapsack().run();
    }

    private void run() throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/com/serheev/solutions/greedy_algorithms/greedy-knapsack-input.txt"));
        int[] backpack = new int[]{
                input.nextInt(), input.nextInt()
        };
        Item[] items = new Item[backpack[0]];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        input.close();

        System.out.println(fillBackpack(backpack, items));
    }

    private void test() {
        // output: 180.000
        int[] backpack1 = new int[]{
                3, 50
        };

        Item[] items1 = new Item[]{
                new Item(60, 20),
                new Item(100, 50),
                new Item(120, 30)
        };

        // output: 7777.731
        int[] backpack2 = new int[]{
                5, 9022
        };

        Item[] items2 = new Item[]{
                new Item(3316, 1601),
                new Item(5375, 8940),
                new Item(2852, 6912),
                new Item(3336, 9926),
                new Item(1717, 8427)
        };

        System.out.println(fillBackpack(backpack2, items2));
    }

    /**
     * Algorithm for filling a backpack with items. Items can be divided into parts.
     * <p>
     * Time complexity O(n log n), Space complexity O(n)
     *
     * @param backpack int[]
     * @param itemList Item[]
     * @return String - Backpack value
     */
    public static String fillBackpack(int[] backpack, Item[] itemList) {
        Arrays.sort(itemList);

        double sum = 0;
        double freeSpace = backpack[1];
        double peaceOfItem;
        double costOfPeace;
        for (Item item : itemList) {
            if (freeSpace - item.getVolume() >= 0) {
                freeSpace -= item.getVolume();
                sum += item.getCost();
            } else {
                peaceOfItem = item.getVolume() - (item.getVolume() - freeSpace);
                costOfPeace = peaceOfItem * ((double) item.getCost() / item.getVolume());
                freeSpace -= peaceOfItem;
                sum += costOfPeace;
            }
        }

        return String.format("%.3f", sum);
    }

    final class Item implements Comparable<Item> {
        private final int cost;
        private final int volume;

        Item(int cost, int volume) {
            this.cost = cost;
            this.volume = volume;
        }

        public int getCost() {
            return cost;
        }

        public int getVolume() {
            return volume;
        }

        @Override
        public int compareTo(Item o) {
            return -Double.compare((double) this.cost / this.volume, (double) o.cost / o.volume);
        }
    }
}
