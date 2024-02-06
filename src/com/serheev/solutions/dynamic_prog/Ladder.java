package com.serheev.solutions.dynamic_prog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Ladder
 * Given a number 1≤n≤10^2 of staircase steps and integers −10^4≤a1,…,an≤10^4, marking the steps. Find the maximum sum
 * that can be obtained by moving up the stairs from the bottom to the nth step, each time ascending one or two steps.
 */
public class Ladder {
    public static void main(String[] args) throws FileNotFoundException {
        new Ladder().run();
    }

    private void run() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/com/serheev/solutions/dynamic_prog/ladder.txt"));
        int n = scanner.nextInt();
        int[] stairs = new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i] = scanner.nextInt();
        }
        System.out.println(countStairsWeightMemory(stairs, n));
    }

    /**
     * Return the maximum sum stairs of ladder
     * <p>
     * Time complexity O(n), Space complexity O(1)
     *
     * @param stairs int[]
     * @param n      number of stairs
     * @return int
     */
    private int countStairsWeightMemory(int[] stairs, int n) {
        switch (n) {
            case 0 -> {
                return 0;
            }
            case 1 -> {
                return stairs[0];
            }
            default -> {
            }
        }

        int prev = stairs[0];
        int curr = Math.max(prev + stairs[1], stairs[1]);

        for (int i = 2; i < n; i++) {
            int next = Math.max(curr, prev) + stairs[i];
            prev = curr;
            curr = next;
        }

        return curr;
    }

    /**
     * Return the maximum sum stairs of ladder
     * <p>
     * Time complexity O(n), Space complexity O(n)
     *
     * @param stairs int[]
     * @param n      number of stairs
     * @return int
     */
    static int countStairsWeightArray(int[] stairs, int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return stairs[0];
        }

        int[] dp = new int[n];

        dp[0] = stairs[0];
        dp[1] = Math.max(stairs[0] + stairs[1], stairs[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2]) + stairs[i];
        }

        return dp[n - 1];
    }
}
