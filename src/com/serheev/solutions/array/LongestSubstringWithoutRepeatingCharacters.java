package com.serheev.solutions.array;

import com.serheev.utils.TestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 3. Longest Substring Without Repeating Characters
 * Task: Given a string s, find the length of the longest substring without repeating characters. *
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("bbbbb")); // 1
        System.out.println(lengthOfLongestSubstring("pwwkew rawwwwwwwwww")); // 6
        System.out.println(lengthOfLongestSubstring(" ")); // 1
        System.out.println(lengthOfLongestSubstring("")); // 0
        System.out.println(lengthOfLongestSubstring(null)); // 0

        TestUtils.executionTime(() -> lengthOfLongestSubstring("pwwkew rawwwwwwwwww"));
        TestUtils.executionTime(() -> lengthOfLongestSubstringWithArray("pwwkew rawwwwwwwwww"));
    }

    /**
     * Using HashMap
     * <p>
     * Time complexity O(N), Space complexity O(N)
     *
     * @param s argument
     * @return int - Maximum Length of Substring Without Repeating Characters
     */
    public static int lengthOfLongestSubstring(String s) {
        if (!ConditionsValidator.meetConditions(s)) {
            return 0;
        }

        Map<Character, Integer> characterIndexMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            Character currentCharacter = s.charAt(right);
            if (characterIndexMap.containsKey(currentCharacter)) {
                left = Math.max(left, characterIndexMap.get(currentCharacter) + 1);
            }
            characterIndexMap.put(currentCharacter, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * Using Array
     * <p>
     * Time complexity O(N), Space complexity O(1)
     *
     * @param s argument
     * @return int - Maximum Length of Substring Without Repeating Characters
     */
    public static int lengthOfLongestSubstringWithArray(String s) {
        if (!ConditionsValidator.meetConditions(s)) {
            return 0;
        }

        int maxLength = 0;
        int[] index = new int[128]; // assuming ASCII 128

        for (int right = 0, left = 0; right < s.length(); right++) {
            left = Math.max(index[s.charAt(right)], left);
            maxLength = Math.max(maxLength, right - left + 1);
            index[s.charAt(right)] = right + 1;
        }
        return maxLength;
    }

    private static final class ConditionsValidator {
        private static final int MAX_LENGTH = 50000;
        private static final Pattern VALID_PATTERN = Pattern.compile("^[\\d\\w\\s\\W]*$");

        public static boolean meetConditions(String s) {
            return s != null && isValidLength(s) && isValidPattern(s);
        }

        private static boolean isValidLength(String s) {
            return s.length() <= MAX_LENGTH;
        }

        private static boolean isValidPattern(String s) {
            return VALID_PATTERN.matcher(s).matches();
        }

        private ConditionsValidator() {
            throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
        }
    }

}
