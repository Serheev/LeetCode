package com.serheev.solutions.other;

import com.serheev.utils.TestUtils;

import java.util.Scanner;

/**
 * Given integers (1 <= n <= 10^18) and (2 <= m <= 10^5),
 * it is necessary to find the remainder of the division of the (n)-th Fibonacci number by (m).
 */
public class FibonacciModulo {

    public static void main(String[] args) {
        TestUtils.Timer timer = TestUtils.Timer.getTimer();
//        Scanner input = new Scanner(System.in);
//        System.out.println(pisanoFibonacciModule(input.nextLong(), input.nextInt()));

        System.out.println(pisanoFibonacciModule(0, 2)); // 0
        System.out.println(pisanoFibonacciModule(1, 2)); // 1
        System.out.println(pisanoFibonacciModule(9, 2)); // 0
        System.out.println(pisanoFibonacciModule(10, 2)); // 1
        System.out.println(pisanoFibonacciModule(1025, 55)); // 5
        System.out.println(pisanoFibonacciModule(12589, 369)); // 89
        System.out.println(pisanoFibonacciModule(1598753, 25897)); // 20305
        System.out.println(pisanoFibonacciModule(60282445765134413L, 2263)); // 974

        timer.start();
        System.out.println(pisanoFibonacciModule(1000000000000000000L, 100000)); // 46875
        timer.stop();
        timer.printDuration();

        timer.start();
        System.out.println(getFibonacciModulo(1000000000000000000L, 100000)); // 46875
        timer.stop();
        timer.printDuration();
    }

    /**
     * Optimized algorithm
     * <p>
     * The remainder of the division of the (n)-th Fibonacci number by (m)
     * <p>
     * Time complexity O(M), Space complexity O(1)
     *
     * @param n long (n)-th Fibonacci number
     * @param m int divider
     * @return int - the remainder of the division of the (n)-th Fibonacci number by (m)
     */
    public static int pisanoFibonacciModule(long n, int m) {
        if (n <= 1) {
            return (int) n;
        }

        int nPrev = 1;
        int nCurr = 1;
        int nNext;
        int pisanoPeriod = 1;

        while (!(nPrev == 0 && nCurr == 1)) {
            nNext = nCurr;
            nCurr = (nCurr + nPrev) % m;
            nPrev = nNext;
            pisanoPeriod++;
        }

        n %= pisanoPeriod;

        for (long i = 1; i <= n; i++) {
            nNext = (nPrev + nCurr) % m;
            nPrev = nCurr;
            nCurr = nNext;
        }

        return nPrev;
    }

    /**
     * The remainder of the division of the (n)-th Fibonacci number by (m)
     * <p>
     * Time complexity O(M), Space complexity O(1)
     *
     * @param n long (n)-th Fibonacci number
     * @param m int divider
     * @return int - the remainder of the division of the (n)-th Fibonacci number by (m)
     */
    public static long getFibonacciModulo(long n, int m) {
        int pisanoPeriod = getPisanoPeriod(m);
        n %= pisanoPeriod;

        if (n <= 1) {
            return n;
        }

        int nPrev = 0;
        int nCurr = 1;
        int nNext;
        for (long i = 2; i <= n; i++) {
            nNext = (nPrev + nCurr) % m;
            nPrev = nCurr;
            nCurr = nNext;
        }

        return nCurr;
    }

    /**
     * Pisano sequence period number
     * <p>
     * Time complexity O(M), Space complexity O(1)
     *
     * @param m int divider
     * @return int - Pisano sequence period number
     */
    public static int getPisanoPeriod(int m) {
        int nPrev = 0;
        int nCurr = 1;
        int nNext;

        for (int i = 0; i < 6 * m; i++) {
            nNext = (nPrev + nCurr) % m;
            nPrev = nCurr;
            nCurr = nNext;

            if (nPrev == 0 && nCurr == 1) {
                return i + 1;
            }
        }

        return 0;
    }
}
