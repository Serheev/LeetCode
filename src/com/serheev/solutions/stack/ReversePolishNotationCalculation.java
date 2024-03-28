package com.serheev.solutions.stack;

import java.util.Arrays;
import java.util.Set;
import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation
 * Task: You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * <p>
 * Time complexity: O(N)
 * Space complexity: O(N)
 *
 * @author Yurii Serheev
 */
public class ReversePolishNotationCalculation {

    private static final Set<String> operators = Set.of("+", "-", "*", "/");

    public static int evalRPN(String[] tokens) {
        Stack<Integer> evalStack = new Stack<>();

        Arrays.stream(tokens).forEach(t -> {
            if (isOperator(t)) {
                evalStack.push(evaluateExp(evalStack.pop(), evalStack.pop(), t));
            } else {
                evalStack.push(Integer.parseInt(t));
            }
        });

        return evalStack.pop();
    }

    public static int evaluateExp(int a, int b, String t) {
        if (t.equals("/") && a == 0) {
            throw new ArithmeticException("division by zero");
        }

        return switch (t) {
            case "+" -> b + a;
            case "-" -> b - a;
            case "*" -> b * a;
            case "/" -> b / a; // reverse order of operands because stack

            default -> throw new IllegalArgumentException("Invalid operator");
        };
    }

    private static boolean isOperator(String operator) {
        return operators.contains(operator);
    }

    public static void main(String[] args) {
        // ((2 + 1) * 3) = 9
        String[] tokens1 = {"2", "1", "+", "3", "*"};

        // (4 + (13 / 5)) = 6
        String[] tokens2 = {"4", "13", "5", "/", "+"};

        /*
          ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
          = ((10 * (6 / (12 * -11))) + 17) + 5
          = ((10 * (6 / -132)) + 17) + 5
          = ((10 * 0) + 17) + 5
          = (0 + 17) + 5
          = 17 + 5
          = 22
         */
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

        // 3 - 4 = -1
        String[] tokens4 = {"4","3","-"};

        System.out.println(evalRPN(tokens1));
        System.out.println(evalRPN(tokens2));
        System.out.println(evalRPN(tokens3));
        System.out.println(evalRPN(tokens4));
    }

}
