package com.serheev.solutions.greedy_algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Cover segments with dots (using Array[n][2]) - works slower.
 * Segments on a straight line are given. Find points that lie on all given segments. The set found must be minimal size.
 * The first line gives the number of segments, where 1 ≤ N ≤ 100. Each of the following N lines contains two numbers
 * 0 ≤ l ≤ r ≤ 10^9, which define the start and end of a segment. Output the optimal number of M points and the M points
 * themselves. If there are several sets of points, output any of them.
 */
public class MinimumPointsForSegmentsArray {
    public static void main(String[] args) throws FileNotFoundException {
        new MinimumPointsForSegmentsArray().run();
    }

    private void run() throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/com/serheev/solutions/greedy_algorithms/minimum-points-for segments-input.txt"));
        int segmentNumber = input.nextInt();
        int[][] segments = new int[segmentNumber][2];
        for (int i = 0; i < segmentNumber; i++) {
            segments[i][0] = input.nextInt();
            segments[i][1] = input.nextInt();
        }

        computeCoveredPoints(segments);
    }

    public void computeCoveredPoints(int[][] segments) {
        Arrays.sort(segments, Comparator.comparingInt(a -> a[1]));

        int points = 0;
        int lastPoint = -1;

        StringBuilder pointsResult = new StringBuilder();
        for (int i = 0; i < segments.length; i++) {
            if (lastPoint < segments[i][0]) {
                lastPoint = segments[i][1];
                points++;
                pointsResult.append(lastPoint).append(" ");
            }
        }

        System.out.println(points);
        System.out.println(pointsResult);
    }
}
