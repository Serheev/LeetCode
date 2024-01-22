package com.serheev.solutions.greedy_algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Given a number 1 <= n <= 10^9, find the maximum number k for which n can be represented as the sum
 * of k different natural addends. Print the number k on the first line and the k addends on the second line.
 */
public class VariousAdditionsNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input number from 1 to 10^9:");
        int number = input.nextInt();

        List<Integer> result = findAdditionsNumber(number);
        System.out.println(result.size());
        for (Integer i : result) {
            System.out.print(i + " ");
        }

//        System.out.println(findAdditionsNumber(1));
//        System.out.println(findAdditionsNumber(2));
//        System.out.println(findAdditionsNumber(3));
//        System.out.println(findAdditionsNumber(4));
//        System.out.println(findAdditionsNumber(5));
//        System.out.println(findAdditionsNumber(6));
//        System.out.println(findAdditionsNumber(7));
    }

    /**
     * Find the maximum number k for which n can be represented as the sum
     * <p>
     * Time complexity O(n), Space complexity O(n)
     *
     * @param number int
     * @return List of Integer
     */
    public static List<Integer> findAdditionsNumber(int number) {
        List<Integer> addends = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (number - i > i) {
                addends.add(i);
                number -= i;
            } else {
                addends.add(number);
                break;
            }
        }

        return addends;
    }

    public static List<Integer> findAdditionsNumberOpt(int number) {
        List<Integer> addends = new ArrayList<>();
        int i = 1;
        while (number > 2 * i) {
            number -= i;
            addends.add(i++);
        }
        addends.add(number);

        return addends;
    }
}
