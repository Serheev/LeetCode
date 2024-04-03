package com.serheev.solutions.dynamic_prog;

/**
 * 5. Longest Palindromic Substring
 * <p>Task: Given a string s, return the longest palindromic substring in s.
 *
 * @author Yurii Serheiev
 */
public class LongestPalindromicSubstring {

    /**
     * DP algorithm with matrix
     * Time complexity: O(n^2),
     * Space complexity: O(n^2)
     *
     * @param s input string
     * @return String longest palindrome
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }

        int start = 0;
        int end = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int length = 1; length <= n; length++) {
            for (int i = 0; i + length - 1 < n; i++) {
                int j = i + length - 1;
                if (s.charAt(i) == s.charAt(j) && (length <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (length > end - start) {
                        start = i;
                        end = j;
                    }
                }
            }
        }

        return s.substring(start, end + 1);
    }

    /**
     * Expand Around Center algorithm
     * Time complexity: O(n^2),
     * Space complexity: O(1)
     *
     * @param s input string
     * @return String longest palindrome
     */
    public String longestPalindromeEAC(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // if palindrome have odd length
            int len2 = expandAroundCenter(s, i, i + 1); // if palindrome have even length
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("babad"));
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("cbbd"));
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("aaaaa"));

        String stringWithoutSpaces = "God a red nugget a fat egg under a dog".replaceAll("\\s", "").toLowerCase();
        System.out.println(new LongestPalindromicSubstring().longestPalindrome(stringWithoutSpaces));

    }
}
