package com.serheev.solutions.other;

import java.util.Scanner;

/**
 * Given an integer (1 <= n <= 40), it is necessary to compute the (n)-th Fibonacci number
 * (recall that (F_0 = 0), (F_1 = 1) and (F_n = F_{n-1} + F_{n-2}) for (n >= 2)).
 */
public class Fibonacci {
    static long[] memo;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(fibonacciMemo(input.nextInt()));
    }

    /**
     * Naive Recursive algorithm
     * <p>
     * Time complexity O(2^N), Space complexity O(N)
     *
     * @param n argument
     * @return int - (n)-th Fibonacci number
     */
    private static int fibonacciNaive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciNaive(n - 2) + fibonacciNaive(n - 1);
    }

    /**
     * Recursive and Memoized Solution
     * <p>
     * Time complexity O(N), Space complexity O(N)
     *
     * @param n argument
     * @return long - (n)-th Fibonacci number
     */
    private static long fibonacciMemo(int n) {
        if (memo == null) {
            memo = new long[n + 1];
        }
        if (n <= 1) {
            return n;
        }

        if (memo[n] != 0) {
            return memo[n];
        }
        return memo[n] = fibonacciMemo(n - 2) + fibonacciMemo(n - 1);
    }

    /**
     * Only two last numbers saved
     * <p>
     * Time complexity O(N), Space complexity O(1)
     *
     * @param n argument
     * @return long - (n)-th Fibonacci number
     */
    private static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        long nPrev = 0;
        long nCurr = 1;
        for (int i = 2; i <= n; ++i) {
            long nNext = nPrev + nCurr;
            nPrev = nCurr;
            nCurr = nNext;
        }
        return nCurr;
    }
}
