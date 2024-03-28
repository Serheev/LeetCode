package com.serheev.solutions.sorting;

import java.util.Arrays;

/**
 * Quick sorting algorithm
 * Time complexity: best-O(n log n), average-O(n log n), worst-O(n^2)
 * Space complexity O(n)
 *
 * @author Yurii Serheev
 */
public class QuickSort {
    private int[] array;

    private void run(int[] array) {
        this.array = array;
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            sort(array, low, pivotIndex - 1);
            sort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {

        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);

        return i + 1;
    }

    private void swap(int i, int j) {
        if (i != j) {
            array[i] = array[i] ^ array[j];
            array[j] = array[i] ^ array[j];
            array[i] = array[i] ^ array[j];
        }
    }

    public static void main(String[] args) {
        int[] array = {7, 6, 1, 2, 4, 3, -9, 0};

        new QuickSort().run(array);
        System.out.println(Arrays.toString(array));
    }
}
