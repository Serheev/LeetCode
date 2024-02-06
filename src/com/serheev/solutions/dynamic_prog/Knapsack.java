package com.serheev.solutions.dynamic_prog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Knapsack
 * The first line of input contains integers 1≤W≤10^4 1≤W≤104 and 1≤N≤300 — the capacity of the backpack and the number
 * of gold bars. The next line contains N integers 0≤w_1,…,w_n≤10^5, representing the weights of the gold bars.
 * Find the maximum weight of gold that can be carried in the backpack.
 */
public class Knapsack {
    public static void main(String[] args) throws FileNotFoundException {
        new Knapsack().run();
    }

    private void run() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/com/serheev/solutions/dynamic_prog/knapsack.txt"));
        int w = scanner.nextInt();
        int n = scanner.nextInt();

        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        System.out.println(knapsackCapacity(weights, n, w));
    }

    /**
     * Return the maximum weight of gold that can be carried in the backpack
     * <p>
     * Time complexity O(n*w), Space complexity O(n*w)
     *
     * @param weights int[]
     * @param n number of bars
     * @param w capacity
     * @return int
     */
    private int knapsackCapacity(int[] weights, int n, int w) {
        int[][] dp = new int[n + 1][w + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + weights[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][w];
    }
}

