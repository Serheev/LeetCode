package com.serheev.solutions.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Task: Given a list of brackets, check this list for balancing using Stack.
 * <p>
 * Time complexity: O(N)
 * Space complexity: O(N)
 *
 * @author Yurii Serheev
 */
public class BalancedParentheses {

    private final static char[] openBrackets = {'(', '{', '['};
    private final static char[] closedBrackets = {')', '}', ']'};

    public static boolean isBalanced(String str) {

        Stack<Character> stack = new Stack<>();

        // binary search requires sorting
        Arrays.sort(openBrackets);
        Arrays.sort(closedBrackets);

        for (char ch : str.toCharArray()) {
            int openBracketIndex = getElementIndex(openBrackets, ch);
            int closedBracketIndex = getElementIndex(closedBrackets, ch);

            if (openBracketIndex >= 0) {
                stack.push(ch);
            } else if (closedBracketIndex >= 0) {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (openBrackets[closedBracketIndex] != top && closedBrackets[closedBracketIndex] == ch) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static int getElementIndex(char[] array, char element) {
        return Arrays.binarySearch(array, element);
    }

    public static void main(String[] args) {
        String unbalancedBrackets = "{,(,),};,[{[(]}";
        String balancedBrackets = "{,(,),},[{[((0);)]}]";
        System.out.println("String is balanced: " + isBalanced(unbalancedBrackets));
        System.out.println("String is balanced: " + isBalanced(balancedBrackets));
    }
}
