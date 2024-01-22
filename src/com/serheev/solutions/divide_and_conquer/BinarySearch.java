package com.serheev.solutions.divide_and_conquer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Binary Search
 * In the first line, an integer
 * (1 <= n <= 10^5) is given, and an array (A[1…n]) of (n) distinct natural numbers, not exceeding (10^9),
 * in ascending order. In the second line, an integer (1 <= k <= 10^5) is given, along with (k) natural numbers
 * (b_1, ..., b_k), not exceeding (10^9). For each (i) from 1 to (k), it is necessary to output the index
 * (1 <= j <= n) for which (A[j] = b_i), or (-1) if there is no such (j).
 */
public class BinarySearch {
    public static void main(String[] args) throws FileNotFoundException {
        new BinarySearch().run();
    }

    private void run() throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/com/serheev/solutions/divide_and_conquer/binary-search-input.txt"));
        int[] firstLine = Arrays.stream(input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] secondLine = Arrays.stream(input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] numbers = new int[firstLine[0]];
        for (int i = 0; i < firstLine[0]; i++) {
            numbers[i] = firstLine[i + 1];
        }

        int[] requiredNumbers = new int[secondLine[0]];
        for (int i = 0; i < secondLine[0]; i++) {
            requiredNumbers[i] = secondLine[i + 1];
        }

        for (int num : requiredNumbers) {
            int index = binarySearch(numbers, num);
            System.out.print(index + " ");
        }
    }

    /**
     * Does not perform sorting, so the data in the array must be pre-sorted.
     * Time complexity O(log n), Space complexity O(1)
     * @param arr int[]
     * @param target int
     * @return
     */
    private int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid + 1; // Adding 1 to get the 1-based index
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
