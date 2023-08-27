package com.serheev.solutions.array;

import com.serheev.utils.TestUtils;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args) {
        case1();
        case2();
        case3();
    }

    private static void case1() {
        int[] arrayA = {1, 2, 3, 0, 0, 0};
        int[] arrayB = {2, 5, 6};
        int numsOfArrayA = 3;
        int numsOfArrayB = 3;

        Solution solution = new Solution("Case 1");
        solution.merge(arrayA, numsOfArrayA, arrayB, numsOfArrayB);
    }

    private static void case2() {
        int[] arrayA = {1};
        int[] arrayB = {2};
        int numsOfArrayA = 1;
        int numsOfArrayB = 0;

        Solution solution = new Solution("Case 2");
        solution.merge(arrayA, numsOfArrayA, arrayB, numsOfArrayB);
    }

    private static void case3() {
        int[] arrayA = {0};
        int[] arrayB = {1};
        int numsOfArrayA = 0;
        int numsOfArrayB = 1;

        Solution solution = new Solution("Case 3");
        solution.merge(arrayA, numsOfArrayA, arrayB, numsOfArrayB);
    }

    static class Solution {
        String nameOfCase;

        Solution(String name) {
            nameOfCase = name;
        }

        private void merge(int[] nums1, int m, int[] nums2, int n) {
            TestUtils.printResult(nameOfCase);

            TestUtils.executionTime(() -> fastSolution(nums1.clone(), m, nums2.clone(), n));
            TestUtils.executionTime(() -> optimalSolution(nums1.clone(), m, nums2.clone(), n));
            TestUtils.executionTime(() -> optimalShortSolution(nums1.clone(), m, nums2.clone(), n));
        }

        private void fastSolution(int[] nums1, int m, int[] nums2, int n) {
            nums1 = Arrays.copyOf(nums1, m + n);
            System.arraycopy(nums2, 0, nums1, m, n);
            Arrays.sort(nums1);

            TestUtils.printMethodName();
            TestUtils.printResult(nums1);
        }

        private void optimalSolution(int[] nums1, int m, int[] nums2, int n) {
            int index1 = m - 1;
            int index2 = n - 1;
            int mergedIndex = m + n - 1;

            while (index1 >= 0 && index2 >= 0) {
                if (nums1[index1] > nums2[index2]) {
                    nums1[mergedIndex] = nums1[index1];
                    index1--;
                } else {
                    nums1[mergedIndex] = nums2[index2];
                    index2--;
                }
                mergedIndex--;
            }

            while (index2 >= 0) {
                nums1[mergedIndex] = nums2[index2];
                index2--;
                mergedIndex--;
            }

            TestUtils.printMethodName();
            TestUtils.printResult(nums1);
        }

        private void optimalShortSolution(int[] nums1, int m, int[] nums2, int n) {
            int i = m - 1;
            int j = n - 1;
            int k = m + n - 1;

            while (j >= 0) {
                if (i >= 0 && nums1[i] > nums2[j]) {
                    nums1[k--] = nums1[i--];
                } else {
                    nums1[k--] = nums2[j--];
                }
            }

            TestUtils.printMethodName();
            TestUtils.printResult(nums1);
        }

    }
}
