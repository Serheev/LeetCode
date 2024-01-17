package com.serheev.solutions.other;

import java.util.Scanner;

/**
 * Given a number (1 <= n <= 10^7), it is necessary to find the last digit of the (n)-th Fibonacci number.
 * As we remember, Fibonacci numbers grow very quickly, so when calculating them, one must be careful with overflow.
 * In this task, however, this problem can be avoided since we are only interested in the last digit of the Fibonacci
 * number: if (0 <= a, b <= 9) are the last digits of the numbers (F_i) and (F_{i+1}) respectively,
 * then ((a+b) mod 10) is the last digit of the number (F_{i+2}).
 */
public class FibonacciLastNumber {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(fibonacciLastNumberOf(input.nextInt()));
//        System.out.println(fibonacciLastNumberOf(841645));
    }

    /**
     * The last digits of the Fibonacci numbers repeat with a period of 60.
     * <p>
     * Time complexity O(N), Space complexity O(1)
     *
     * @param n argument
     * @return int - The last digits of the Fibonacci number
     */
    private static int fibonacciLastNumberOf(int n) {
        if (n <= 1) {
            return n;
        }
        int nPrev = 0;
        int nCurr = 1;
        /**
         *  The last digits of the Fibonacci numbers repeat with a period of 60.
         *  This means that the last digit of the (n)th Fibonacci number will be the same as the last digit
         *  of the Fibonacci number numbered (n) modulo 60.
         */
        for (int i = 2; i <= n % 60; ++i) {
            int nNext = (nPrev + nCurr) % 10; // Next Fibonacci number's last
            nPrev = nCurr;
            nCurr = nNext;
        }
        return nCurr;
    }
}
