package com.serheev.solutions.sorting;

import java.util.Arrays;

/**
 * Bubble sorting algorithm
 * Time complexity: best - O(n), average-O(n^2), worst O(n^2)
 * Space complexity O(1)
 *
 * @author Yurii Serheev
 */
public class BubbleSort {
    private int[] array;

    private void run(int[] array) {
        this.array = array;

        boolean isSorted = false;
        int lastElement = array.length - 1;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < lastElement; i++) {
                if (array[i] > array[i + 1]) {
                    swap(i, i + 1);
                    isSorted = false;
                }
            }
            lastElement--;
        }

    }

    private void swap(int i, int j) {
        if (i != j) {
            array[i] = array[i] ^ array[j];
            array[j] = array[i] ^ array[j];
            array[i] = array[i] ^ array[j];
        }
    }

    public static void main(String[] args) {
        int[] array = {7, 6, 1, 2, 4, 3, 9, 0};

        new BubbleSort().run(array);
        System.out.println(Arrays.toString(array));
    }
}
