package com.serheev.solutions.graph;

import java.util.*;

/**
 * 127. Word Ladder
 * <p>Task: A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of
 * words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <ul>
 *     <li>Every adjacent pair of words differs by a single letter.</li>
 *     <li>Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.</li>
 *     <li>sk == endWord</li>
 * </ul>
 * <p>
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 * @author Yurii Serheev
 */
public class WordLadder {
    private Set<String> wordSet;

    /**
     * Quick solving
     * <p>Time complexity: O(n*m*26)
     * <p>Space complexity: O(n*m)
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return int number of words in the shortest transformation sequence from beginWord to endWord
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList);

        if (!dictionary.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                String currentWord = queue.poll();
                char[] wordChars = currentWord.toCharArray();

                for (int j = 0; j < wordChars.length; j++) {
                    char origChar = wordChars[j];

                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (wordChars[j] == ch) {
                            continue;
                        }

                        wordChars[j] = ch;
                        String newWord = String.valueOf(wordChars);
                        if (newWord.equals(endWord)) {
                            return level + 1;
                        }

                        if (dictionary.contains(newWord)) {
                            queue.offer(newWord);
                            dictionary.remove(newWord);
                        }
                    }
                    wordChars[j] = origChar;
                }
            }
            level++;
        }
        return 0;
    }


    /**
     * Solving a problem with bidirectional BFS https://algo.monster/liteproblems/127
     * <p>Time complexity: O(n*m*26)
     * <p>Space complexity: O(n*m)
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return int number of words in the shortest transformation sequence from beginWord to endWord
     */
    public int ladderLengthBiBFS(String beginWord, String endWord, List<String> wordList) {
        // Initialize the word set with the given word list
        wordSet = new HashSet<>(wordList);
        // If the endWord is not in the wordList, return 0 as no ladder exists
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        // Use two queues to implement bidirectional BFS
        Queue<String> queueBegin = new ArrayDeque<>();
        Queue<String> queueEnd = new ArrayDeque<>();
        // Maps to keep track of the path lengths from the begin and end words
        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        // Initialize the queues and maps
        queueBegin.offer(beginWord);
        queueEnd.offer(endWord);
        visitedBegin.put(beginWord, 1); // The step count starts at 1
        visitedEnd.put(endWord, 1);

        // Perform BFS until one of the queues is empty
        while (!queueBegin.isEmpty() && !queueEnd.isEmpty()) {
            // Always extend the smaller queue in the current iteration
            int result = queueBegin.size() <= queueEnd.size() ?
                    extendQueue(visitedBegin, visitedEnd, queueBegin) :
                    extendQueue(visitedEnd, visitedBegin, queueEnd);
            // If a connection is found, return the total length of the path
            if (result != -1) {
                return result;
            }
        }
        // If no path is found, return 0
        return 0;
    }

    private int extendQueue(Map<String, Integer> visitedOne, Map<String, Integer> visitedOther, Queue<String> queue) {
        // Process each word in the current layer
        for (int i = queue.size(); i > 0; --i) {
            String currentWord = queue.poll();
            int currentStep = visitedOne.get(currentWord);
            char[] characters = currentWord.toCharArray();
            // Try changing every character to make new words
            for (int j = 0; j < characters.length; ++j) {
                char originalChar = characters[j];
                for (char ch = 'a'; ch <= 'z'; ++ch) {
                    characters[j] = ch;
                    String newWord = new String(characters);
                    // Skip if the new word is not in the word set or already visited
                    if (!wordSet.contains(newWord) || visitedOne.containsKey(newWord)) {
                        continue;
                    }
                    // If the new word is in the other visited map, a path is found
                    if (visitedOther.containsKey(newWord)) {
                        return currentStep + visitedOther.get(newWord);
                    }
                    // Otherwise, add the new word to the queue and visited map
                    queue.offer(newWord);
                    visitedOne.put(newWord, currentStep + 1);
                }
                // Restore the original character
                characters[j] = originalChar;
            }
        }
        // If no progress is made in this direction, return -1
        return -1;
    }

    public static void main(String[] args) {
        //Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
        //Output: 5
        System.out.println(new WordLadder().ladderLengthBiBFS("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }
}
