package com.serheev.solutions.other;

import com.serheev.utils.TestUtils;

/**
 * Given two numbers where 1 ≤ a, b ≤ 2⋅10^9, find their greatest common divisor.
 */
public class GCD {
    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        System.out.println(findGCD(input.nextLong(), input.nextLong()));

        System.out.println(findGCD(0, 0)); // 0
        System.out.println(findGCD(0, 1)); // 1
        System.out.println(findGCD(1, 0)); // 1
        System.out.println(findGCD(1, 1)); // 1

        TestUtils.Timer timer = TestUtils.Timer.getTimer();
        timer.start();
        System.out.println(findGCD(14159572, 63967072)); // 4
        timer.stop();
        timer.printDuration();

        timer.start();
        System.out.println(findGCDFast(14159572, 63967072)); // 4
        timer.stop();
        timer.printDuration();
    }

    /**
     * Optimized Finding GCD algorithm
     * <p>
     * Time complexity O(log(min(a, b))), Space complexity O(1)
     * <p>
     * The time complexity of this function is O(log(min(a, b))). This is because in each iteration of the while loop,
     * either a or b is reduced to their remainder when divided by the other, effectively halving the size
     * of the larger number. The number of iterations is proportional to the number of bits in min(a, b),
     * hence the logarithmic time complexity.
     * <p>
     * The space complexity of this function is O(1). This is because the amount of space used does not change
     * with the size of the input numbers a and b. The function only uses a constant amount of space to store
     * the variables a and b, and does not use any additional data structures that grow with the input size.
     *
     * @param a long
     * @param b long
     * @return int - GCD (Greatest Common Divider)
     */
    private static int findGCDFast(long a, long b) {
        while (a > 0 && b > 0) {
            if (a > b)
                a %= b;
            else
                b %= a;
        }

        return (int) a;
    }

    private static int findGCD(long a, long b) {
        while (b != 0) {
            long temp = a;
            a = b;
            b = temp % b;
        }

        return (int) a;
    }

    /**
     * Naive Algorithm is not recommended
     * <p>
     * Naive algorithm has a time complexity of O(min(a, b)), which can be quite slow for large numbers.
     */
    public static int findGCDNaive(int a, int b) {
        int gcd = 1;
        for (int i = 1; i <= Math.min(a, b); i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }

        return gcd;
    }
}
