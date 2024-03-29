package com.serheev.solutions.search;

/**
 * 79. Word Search
 * Task: Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or
 * vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * Used DFS algorithm <a href="https://en.wikipedia.org/wiki/Depth-first_search">en.wikipedia.org/wiki/Depth-first_search</a>
 * <p>
 * Time complexity: O(|M| + |N|)
 * Space complexity: O(|N|)
 *
 * @author Yurii Serheev
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int k) {
        int m = board.length;
        int n = board[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != word.charAt(k)) {
            return false;
        }

        if (k == word.length() - 1) {
            return true;
        }

        char temp = board[i][j];
        board[i][j] = '*'; // mark the cell as visited

        boolean found = dfs(board, word, i + 1, j, k + 1)
                || dfs(board, word, i - 1, j, k + 1)
                || dfs(board, word, i, j + 1, k + 1)
                || dfs(board, word, i, j - 1, k + 1);

        board[i][j] = temp; // restore the cell

        return found;
    }

    public static void main(String[] args) {
        char[][] board1 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        char[][] board2 = {{'A', 'A'}};
        System.out.println(new WordSearch().exist(board1, "ABCCED"));
        System.out.println(new WordSearch().exist(board2, "AA"));
    }
}
