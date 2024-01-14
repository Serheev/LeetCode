package com.serheev.solutions.array;

import com.serheev.utils.TestUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PythagoreanTriplets {
    public static void main(String[] args) {
        TestUtils.Timer timer = TestUtils.Timer.getTimer();
        timer.start();
        System.out.println(hasPythagoreanTripletOpt(new int[]{3, 1, 4, 6, 5})); // true (3, 4, 5)
        System.out.println(hasPythagoreanTripletOpt(new int[]{5, 4, 6, 12, 13})); // true (5, 12, 13)
        System.out.println(hasPythagoreanTripletOpt(new int[]{10, 4, 6})); // false
        System.out.println(hasPythagoreanTripletOpt(new int[]{1, 2, 3})); // false
        System.out.println(hasPythagoreanTripletOpt(new int[]{8, 17, 15, 24, 7})); // true (8, 15, 17)
        timer.stop();
        timer.printDuration();

        timer.start();
        System.out.println(hasPythagoreanTriplet(new int[]{3, 1, 4, 6, 5})); // true (3, 4, 5)
        System.out.println(hasPythagoreanTriplet(new int[]{5, 4, 6, 12, 13})); // true (5, 12, 13)
        System.out.println(hasPythagoreanTriplet(new int[]{10, 4, 6})); // false
        System.out.println(hasPythagoreanTriplet(new int[]{1, 2, 3})); // false
        System.out.println(hasPythagoreanTriplet(new int[]{8, 17, 15, 24, 7})); // true (8, 15, 17)
        timer.stop();
        timer.printDuration();
    }

    public static boolean hasPythagoreanTriplet(int[] elements) {
        if (elements == null || elements.length < 3) {
            return false;
        }
        // Square all elements
        for (int i = 0; i < elements.length; i++) {
            elements[i] = elements[i] * elements[i];
        }
        // Sort the squared elements
        Arrays.sort(elements);
        // Check for triplets
        for (int i = elements.length - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;
            while (left < right) {
                if (elements[left] + elements[right] == elements[i]) {
                    return true;
                } else if (elements[left] + elements[right] < elements[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return false;
    }

    public static boolean hasPythagoreanTripletOpt(int[] elements) {
        if (elements == null || elements.length < 3) {
            return false;
        }

        // Save squares of elements
        Set<Integer> squares = new HashSet<>();
        for (int e : elements) {
            squares.add(e * e);
        }

        // Check for triplets
        for (int i = 0; i < elements.length; i++) {
            int aSquare = elements[i] * elements[i];
            for (int j = i + 1; j < elements.length; j++) {
                int bSquare = elements[j] * elements[j];
                if (squares.contains(aSquare + bSquare)) {
                    return true;
                }
            }
        }
        return false;

    }
}