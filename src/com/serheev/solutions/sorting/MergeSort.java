package com.serheev.solutions.sorting;

import java.util.Arrays;

/**
 * Quick sorting algorithm
 * Time complexity: best-O(n log n), average-O(n log n), worst-O(n log n)
 * Space complexity O(n)
 *
 * @author Yurii Serheev
 */
public class MergeSort {
    private int[] array;

    private void run(int[] array) {
        this.array = array;

        if (array == null || array.length < 2) {
            return;
        }
        sort(array, new int[array.length], 0, array.length - 1);
    }

    private static void sort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, temp, left, mid);
            sort(arr, temp, mid + 1, right);
            merge(arr, temp, left, right);
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int right) {
        System.arraycopy(arr, left, temp, left, right - left + 1);

        int index = left;
        int leftEnd = (left + right) / 2;
        int leftStart = leftEnd + 1;

        while (left <= leftEnd && leftStart <= right) {
            if (temp[left] <= temp[leftStart]) {
                arr[index++] = temp[left++];
            } else {
                arr[index++] = temp[leftStart++];
            }
        }

        while (left <= leftEnd) {
            arr[index++] = temp[left++];
        }
        while (leftStart <= right) {
            arr[index++] = temp[leftStart++];
        }
    }

    public static void main(String[] args) {
        int[] array = {7, 6, 1, 2, 4, 3, -9, 0};

        new MergeSort().run(array);
        System.out.println(Arrays.toString(array));
    }
}
