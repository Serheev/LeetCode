package com.serheev.solutions.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

/**
 * 224. Basic Calculator
 * Task: Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the
 * result of the evaluation.
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions,
 * such as eval().
 * <p>
 * Time complexity: O(N)
 * Space complexity: O(N)
 *
 * @author Yurii Serheev
 */
public class BasicCalculator {

    public int i = 0;

    /**
     * Time complexity: O(N)
     * Space complexity: O(1) - O(N)
     */
    public int calculate(String s) {
        int result = 0;
        int num = 0;
        int sign = 1;

        while (i < s.length()) {
            char c = s.charAt(i++);

            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                num = calculate(s);
            } else if (c == ')') {
                return result + sign * num;
            } else if (c == '+' || c == '-') {
                result += sign * num;
                num = 0;
                sign = c == '-' ? -1 : 1;
            }
        }

        return result + sign * num;
    }

    public static int calculateWithShuntingYard(String s) {
        return evaluatePostfix(convertToPostfix(tokenize(s)));
    }

    private static String[] tokenize(String s) {
        int n = s.length();
        int index = 0;
        String[] tokens = new String[n];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else if (!Character.isWhitespace(ch)) {
                if (sb.length() > 0) {
                    tokens[index++] = sb.toString();
                    sb.setLength(0);
                }
                tokens[index++] = String.valueOf(ch);
            }
        }

        if (sb.length() > 0) {
            tokens[index++] = sb.toString();
        }

        return Arrays.copyOf(tokens, index);
    }

    /**
     * <a href="https://en.wikipedia.org/wiki/Shunting_yard_algorithm">Shunting yard algorithm</a> implementation.
     * It produces either a postfix notation string, also known as <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">Reverse Polish notation (RPN)</a>
     * <p>
     * Time complexity: O(N)
     * Space complexity: O(N)
     *
     * @param tokens
     * @return String[]
     */
    private static String[] convertToPostfix(String[] tokens) {
        Queue<String> postfixQueue = new ArrayDeque<>();
        Stack<String> stackOperators = new Stack<>();
        boolean check = false;

        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                postfixQueue.add(token);
                check = false;
            } else if (token.equals("(")) {
                stackOperators.push(token);
                check = true;
            } else if (token.equals(")")) {
                while (!stackOperators.isEmpty() && !stackOperators.peek().equals("(")) {
                    postfixQueue.add(stackOperators.pop());
                }
                stackOperators.pop();
            } else {
                if (token.equals("-") && check) {
                    postfixQueue.add("0");
                }
                while (!stackOperators.isEmpty() && precedence(token) <= precedence(stackOperators.peek())) {
                    postfixQueue.add(stackOperators.pop());
                }
                stackOperators.push(token);
            }
        }

        while (!stackOperators.isEmpty()) {
            postfixQueue.add(stackOperators.pop());
        }

        return postfixQueue.toArray(new String[0]);
    }

    private static int precedence(String op) {
        if (op.equals("+") || op.equals("-")) {
            return 1;
        } else if (op.equals("*") || op.equals("/")) {
            return 2;
        } else {
            return 0;
        }
    }

    private static int evaluatePostfix(String[] postfix) {
        Stack<Integer> stack = new Stack<>();

        for (String token : postfix) {
            if (Character.isDigit(token.charAt(0))) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.isEmpty() ? 0 : stack.pop();
                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> stack.push(a / b);
                }
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(calculateWithShuntingYard("-   111 + 111"));
        System.out.println(calculateWithShuntingYard(" 2-1 + 2 "));
        System.out.println(calculateWithShuntingYard("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(calculateWithShuntingYard("2147483647"));
        System.out.println(calculateWithShuntingYard("1-(     -2)"));
        System.out.println(calculateWithShuntingYard("2-(5-6)"));
        System.out.println();
        System.out.println(new BasicCalculator().calculate("-   111 + 111"));
        System.out.println(new BasicCalculator().calculate(" 2-1 + 2 "));
        System.out.println(new BasicCalculator().calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(new BasicCalculator().calculate("2147483647"));
        System.out.println(new BasicCalculator().calculate("1-(     -2)"));
        System.out.println(new BasicCalculator().calculate("2-(5-6)"));

    }
}
