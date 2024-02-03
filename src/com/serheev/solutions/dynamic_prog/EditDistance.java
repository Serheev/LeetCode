package com.serheev.solutions.dynamic_prog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Edit Distance
 * Calculate the edit distance of two given non-empty strings of length no more than 10^2, containing lowercase
 * letters of the Latin alphabet.
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
     * Time complexity O(m*n), Space complexity O(m*n)
     *
     * @param str1 String
     * @param str2 String
     * @return int - Edit distance for two words
     */
    public static int editDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
            }
        }

        return dp[m][n];
    }
}
