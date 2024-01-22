package com.serheev.solutions.greedy_algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * The given non-empty string, s, of length not exceeding 10^4, consisting of lowercase Latin alphabet letters,
 * needs to be encoded with an optimal prefix code. In the first line, output the number of different letters k that
 * occur in the string and the size of the resulting encoded string. In the next k lines, write the codes for each
 * letter in the format "letter: code". In the last line, output the encoded string.
 */
public class HuffmanCoding {
    public static void main(String[] args) throws FileNotFoundException {
        new HuffmanCoding().run();
    }

    private void run() throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/com/serheev/solutions/greedy_algorithms/huffman-coding-input.txt"));
        stringToHuffmanCode(input.nextLine());
    }

    private void stringToHuffmanCode(String string) {
        Map<Character, String> huffmanCode = buildHuffmanCode(string);
        String encodedString = encodeString(string, huffmanCode);

        System.out.println(huffmanCode.size() + " " + encodedString.length());
        for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println(encodedString);
    }


    private Map<Character, String> buildHuffmanCode(String s) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        if (frequencyMap.size() == 1) {
            char singleChar = frequencyMap.keySet().iterator().next();
            Map<Character, String> huffmanCode = new HashMap<>();
            huffmanCode.put(singleChar, "0");
            return huffmanCode;
        }

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode newNode = new HuffmanNode('\0', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;

            priorityQueue.add(newNode);
        }

        HuffmanNode root = priorityQueue.poll();
        Map<Character, String> huffmanCode = new HashMap<>();
        buildCode(root, "", huffmanCode);

        return huffmanCode;
    }

    private void buildCode(HuffmanNode node, String code, Map<Character, String> huffmanCode) {
        if (node != null) {
            if (node.character != '\0') {
                huffmanCode.put(node.character, code);
            }
            buildCode(node.left, code + "0", huffmanCode);
            buildCode(node.right, code + "1", huffmanCode);
        }
    }

    private int encodedStringLength(Map<Character, String> huffmanCode) {
        return huffmanCode.values().stream().mapToInt(String::length).sum();
    }

    private String encodeString(String inputString, Map<Character, String> huffmanCode) {
        StringBuilder encodedString = new StringBuilder();
        for (char c : inputString.toCharArray()) {
            encodedString.append(huffmanCode.get(c));
        }
        return encodedString.toString();
    }

    class HuffmanNode implements Comparable<HuffmanNode> {
        char character;
        int frequency;
        HuffmanNode left, right;

        public HuffmanNode(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        public HuffmanNode(char character) {
            this.character = character;
        }

        @Override
        public int compareTo(HuffmanNode other) {
            return this.frequency - other.frequency;
        }
    }
}
