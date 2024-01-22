package com.serheev.solutions.greedy_algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Cover all segments with the minimum number of points.
 * Segments on a straight line are given. Find points that lie on all given segments. The set found must be minimal size.
 * The first line gives the number of segments, where 1 ≤ N ≤ 100. Each of the following N lines contains two numbers
 * 0 ≤ l ≤ r ≤ 10^9, which define the start and end of a segment. Output the optimal number of M points and the M points
 * themselves. If there are several sets of points, output any of them.
 */
public class MinimumPointsForSegments {

    public static void main(String[] args) throws FileNotFoundException {
        new MinimumPointsForSegments().run();
    }

    private void run() throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/com/serheev/solutions/greedy_algorithms/minimum-points-for segments-input.txt"));
        int numberOfSegments = input.nextInt();
        Segment[] segments = new Segment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; i++) {
            segments[i] = new Segment(input.nextInt(), input.nextInt());
        }
        input.close();

        printResult(computeCoveredPoints(segments));
    }

    private void test() {
        // 2
        // 3 6
        Segment[] testCase1 = new Segment[]{
                new Segment(4, 7),
                new Segment(1, 3),
                new Segment(2, 5),
                new Segment(5, 6)
        };

        // 1
        // 3
        Segment[] testCase2 = new Segment[]{
                new Segment(1, 3),
                new Segment(2, 5),
                new Segment(3, 6)
        };

        // 1
        // 6
        Segment[] testCase3 = new Segment[]{
                new Segment(5, 6),
                new Segment(4, 7),
                new Segment(3, 8),
                new Segment(2, 9),
                new Segment(1, 10)
        };

        // 3
        // 2 4 6
        Segment[] testCase4 = new Segment[]{
                new Segment(1, 2),
                new Segment(2, 3),
                new Segment(3, 4),
                new Segment(4, 5),
                new Segment(5, 6)
        };

        // 5
        // 2 4 6 8 10
        Segment[] testCase5 = new Segment[]{
                new Segment(1, 2),
                new Segment(3, 4),
                new Segment(5, 6),
                new Segment(7, 8),
                new Segment(9, 10)
        };

        // 2
        // 1 7
        Segment[] testCase6 = new Segment[]{
                new Segment(4, 7),
                new Segment(0, 1)
        };

        System.out.println("\n1. ----");
        printResult(computeCoveredPoints(testCase1));
        System.out.println("\n2. ----");
        printResult(computeCoveredPoints(testCase2));
        System.out.println("\n3. ----");
        printResult(computeCoveredPoints(testCase3));
        System.out.println("\n4. ----");
        printResult(computeCoveredPoints(testCase4));
        System.out.println("\n5. ----");
        printResult(computeCoveredPoints(testCase5));
        System.out.println("\n6. ----");
        printResult(computeCoveredPoints(testCase6));
    }

    /**
     * Algorithm for finding common points for all segments
     * <p>
     * Time complexity O(n log n), Space complexity O(n)
     *
     * @param segments Segment[]
     * @return Set<Integer> - common points for all segments
     */
    public static Set<Integer> computeCoveredPoints(Segment[] segments) {
        Arrays.sort(segments);

        Set<Integer> points = new HashSet<>();
        int currentPoint = -1;
        for (int i = 0; i < segments.length; i++) {
            if (currentPoint < segments[i].getL()) {
                currentPoint = segments[i].getR();
                points.add(currentPoint);
            }
        }

        return points;
    }

    public static void printResult(Set<Integer> result) {
        System.out.println(result.size());
        for (Integer point : result) {
            System.out.print(point + " ");
        }
    }

    private final class Segment implements Comparable<Segment> {
        private final int l;
        private final int r;

        Segment(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public int getL() {
            return l;
        }

        public int getR() {
            return r;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", l, r);
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.r, o.r);
        }
    }
}
