package com.serheev.solutions.array;

import java.util.Arrays;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 215. Kth Largest Element in an Array
 * Task: Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Can you solve it without sorting?
 *
 * @author Yurii Serheev
 */
public class KthLargestElementInArray {

    /**
     * Time complexity: O(N logN)
     * Space complexity: O(1)
     *
     * @param nums
     * @param k
     * @return int
     */
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * Time complexity: O(logK) - O(N logK)
     * Space complexity: O(K)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargestHeap(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int n : nums) {
            queue.offer(n);

            if (queue.size() > k) {
                queue.poll();
            }
        }

        return Optional.ofNullable(queue.peek()).orElse(-1);
    }


    /**
     * Time complexity: O(N) - O(N^2)
     * Space complexity: O(logN) - O(N)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargestQuickSelect(int[] nums, int k) {
        if (k < 1 || k > nums.length) {
            return -1;
        }

        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private static int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        int pivot = left + new Random().nextInt(right - left + 1);
        pivot = partition(nums, left, right, pivot);

        if (k == pivot) {
            return nums[k];
        } else if (k < pivot) {
            return quickSelect(nums, left, pivot - 1, k);
        } else {
            return quickSelect(nums, pivot + 1, right, k);
        }
    }

    private static int partition(int[] nums, int left, int right, int pivot) {
        int value = nums[pivot];
        int index = left;

        swap(nums, pivot, right);

        for (int i = left; i < right; i++) {
            if (nums[i] < value) {
                swap(nums, index, i);
                index++;
            }
        }

        swap(nums, right, index);

        return index;
    }

    private static void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }

    public static void main(String[] args) {
        int[] array1 = {3, 2, 1, 5, 6, 4};
        int[] array2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};

        System.out.println(findKthLargest(array1, 2)); // Expected 5
        System.out.println(findKthLargest(array2, 4)); // Expected 4
        System.out.println();
        System.out.println(findKthLargestHeap(array1, 2)); // Expected 5
        System.out.println(findKthLargestHeap(array2, 4)); // Expected 4
        System.out.println();
        System.out.println(findKthLargestQuickSelect(array1, 2)); // Expected 5
        System.out.println(findKthLargestQuickSelect(array2, 4)); // Expected 4
    }
}
