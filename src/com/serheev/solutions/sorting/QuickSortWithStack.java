package com.serheev.solutions.sorting;

import java.util.Arrays;
import java.util.Stack;

/**
 * Quick sorting algorithm with Stack
 * Time complexity: best-O(n log n), average-O(n log n), worst-O(n^2)
 * Space complexity O(n)
 *
 * @author Yurii Serheev
 */
public class QuickSortWithStack {
    private int[] array;

    private void run(int[] array) {
        this.array = array;
        sort(array);
    }

    public void sort(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(arr.length - 1);

        while (!stack.isEmpty()) {
            int high = stack.pop();
            int low = stack.pop();

            if (low < high) {
                int pivotIndex = partition(arr, low, high);
                stack.push(low);
                stack.push(pivotIndex - 1);
                stack.push(pivotIndex + 1);
                stack.push(high);
            }
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

        new QuickSortWithStack().run(array);
        System.out.println(Arrays.toString(array));
    }

}
