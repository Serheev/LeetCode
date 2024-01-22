package com.serheev.solutions.greedy_algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Custom Priority Queue
 * The first line of the input contains the number of operations, where (1 <= n <= 10^5). Each of the following (n) lines
 * specifies an operation of one of the two types:
 * - `Insert x`, where (0 <= x <= 10^9) is an integer.
 * - `ExtractMax`.
 * The first operation adds the number (x) to the priority queue, and the second operation extracts the maximum number
 * and prints it.
 */
public class PriorityQueue {
    private static final int MAX_SIZE = 100000;
    private final int[] heap;
    private int size;

    public PriorityQueue() {
        heap = new int[MAX_SIZE];
        size = 0;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void siftUp(int index) {
        while (index > 0 && heap[parent(index)] < heap[index]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void siftDown(int index) {
        int maxIndex = index;
        int leftChild = leftChild(index);
        if (leftChild <= size - 1 && heap[leftChild] > heap[maxIndex]) {
            maxIndex = leftChild;
        }
        int rightChild = rightChild(index);
        if (rightChild <= size - 1 && heap[rightChild] > heap[maxIndex]) {
            maxIndex = rightChild;
        }
        if (index != maxIndex) {
            swap(index, maxIndex);
            siftDown(maxIndex);
        }
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    public void insert(int x) {
        if (size == MAX_SIZE) {
            System.out.println("Queue is full");
            return;
        }

        size++;
        heap[size - 1] = x;
        siftUp(size - 1);
    }

    public int extractMax() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return -1;
        }

        int result = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new PriorityQueue().run();
    }

    private void run() throws FileNotFoundException {
//      Output:
//      200
//      500
        Scanner scanner = new Scanner(new File("src/com/serheev/solutions/greedy_algorithms/priority-queue-input.txt"));
        int n = scanner.nextInt();

        PriorityQueue priorityQueue = new PriorityQueue();

        for (int i = 0; i < n; i++) {
            String operation = scanner.next();
            if (operation.equals("Insert")) {
                int x = scanner.nextInt();
                priorityQueue.insert(x);
            } else if (operation.equals("ExtractMax")) {
                int max = priorityQueue.extractMax();
                if (max != -1) {
                    System.out.println(max);
                }
            }
        }

    }
}

