package com.serheev.solutions.linkedlist;

/**
 * 19. Remove Nth Node From End of List
 * Task: Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * @author Yurii Serheev
 */
public class RemoveNthNodeFromEndList {

    /**
     * With counting nodes
     * <p>
     * Time complexity: O(N)
     * Space complexity: O(1)
     *
     * @param head ListNode
     * @param n    Nth element to deleting
     * @return ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode pointer = head;
        int lengthOfList = 0;
        while (pointer != null) {
            pointer = pointer.next;
            lengthOfList++;
        }

        // remove first node
        int first = lengthOfList - n + 1;
        if (first == 1) {
            return head.next;
        }

        // remove non-first node
        pointer = head;
        int i = 0;
        while (pointer != null) {
            i++;
            if (i == first - 1) {
                pointer.next = pointer.next.next;
            }
            pointer = pointer.next;
        }

        return head;
    }

    /**
     * <a href="https://medium.com/@arifimran5/fast-and-slow-pointer-pattern-in-linked-list-43647869ac99">Fast and Slow Pointers algorithm</a>
     * <p>
     * Time complexity: O(N)
     * Space complexity: O(1)
     *
     * @param head ListNode
     * @param n    Nth element to deleting
     * @return ListNode
     */
    public ListNode removeNthFromEndOnePass(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // remove the first node
        if (fast == null) {
            head = head.next;

            return head;
        }

        // remove non-first node
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }

    /**
     * Definition for singly-linked list
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        System.out.print("Input: ");
        printList(node1);

        ListNode result = new RemoveNthNodeFromEndList().removeNthFromEndOnePass(node1, 2);

        System.out.print("\nOutput: ");
        printList(result);
    }
}
