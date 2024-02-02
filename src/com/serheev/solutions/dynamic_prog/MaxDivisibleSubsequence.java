package com.serheev.solutions.dynamic_prog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Largest sequential subsequence
 * Given an integer 1 <= n <= 10^3 and an array A[1 ... n] of natural numbers not exceeding 2*10^9.
 * Print the maximum 1 <= k <= n, for which there is a subsequence 1 <= i_1 < i_2 < .. < i_k <= n of length k, in which
 * each element is divisible by the previous one (formally: for all 1 <= j < k, A[i_j] | A[i_j+1]).
 */
public class MaxDivisibleSubsequence {
    public static void main(String[] args) throws IOException {
        new MaxDivisibleSubsequence().run();
    }

    private void run() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/com/serheev/solutions/dynamic_prog/max-divisible-subsequence.txt"));
        int length = Integer.parseInt(bufferedReader.readLine());
        int[] numArray = new int[length];

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < length; i++) {
            numArray[i] = Integer.parseInt(tokenizer.nextToken());
        }

        System.out.println("Input: " + Arrays.toString(numArray));
        System.out.println("Output: " + Arrays.toString(maxDivisibleSubsequence(numArray)));

        bufferedReader.close();
    }

    /**
     * Return the largest sequential subsequence in which each element is divisible by the previous one
     * <p>
     * Time complexity O(n^2), Space complexity O(n)
     *
     * @param nums int[]
     * @return int[] - Greatest sequential subsequence
     */
    private static int[] maxDivisibleSubsequence(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        int maxLength = 0;
        int maxIndex = 0;
        int[] prev = new int[length];
        for (int i = 0; i < length; i++) {

            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    prev[i] = j;
                }
            }

            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }

        //   dp = [1,2,1,3]
        // nums = [3,6,7,12]
        int[] subsequence = new int[maxLength];
        for (int i = maxLength - 1; i >= 0; i--) {
            subsequence[i] = nums[maxIndex];
            maxIndex = prev[maxIndex];
        }

        return subsequence;
    }
}
