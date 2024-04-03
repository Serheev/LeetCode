package com.serheev.solutions.dynamic_prog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 72. Edit Distance
 * <p>Task: Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * <p>You have the following three operations permitted on a word:
 *<ul>
 * <li>Insert a character</li>
 * <li>Delete a character</li>
 * <li>Replace a character</li>
 * </ul>
 * @author Yurii Serheiev
 */
public class EditDistance {

    public static void main(String[] args) throws FileNotFoundException {
        new EditDistance().run();
    }

    private void run() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/com/serheev/solutions/dynamic_prog/edit-distance.txt"));
        String aWord = scanner.nextLine();
        String bWord = scanner.nextLine();

        System.out.println(editDistance(aWord, bWord));
    }

    /**
     * Return the Edit Distance for two words
     * <p>
     * Time complexity: O(n*m),
     * Space complexity: O(n*m)
     *
     * @param string1 String
     * @param string2 String
     * @return int - Edit distance for two words
     */
    public int editDistance(String string1, String string2) {
        int n = string1.length();
        int m = string2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (string1.charAt(i - 1) == string2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
            }
        }

        return dp[n][m];
    }

    /**
     * Return the Edit Distance for two words
     * <p>
     * Time complexity: O(n*m)
     * Space complexity: O(m)
     *
     * @param string1 String
     * @param string2 String
     * @return int - Edit distance for two words
     */
    public int editDistanceOpt(String string1, String string2) {
        int n = string1.length();
        int m = string2.length();
        int[] dp = new int[m + 1];

        for (int i = 0; i <= n; i++) {
            int prev = i;
            for (int j = 0; j <= m; j++) {
                int temp = dp[j];
                if (i == 0)
                    dp[j] = j;
                else if (j == 0)
                    dp[j] = i;
                else if (string1.charAt(i - 1) == string2.charAt(j - 1))
                    dp[j] = prev;
                else
                    dp[j] = 1 + Math.min(Math.min(dp[j], dp[j - 1]), prev);
                prev = temp;
            }
        }

        return dp[m];
    }

}
