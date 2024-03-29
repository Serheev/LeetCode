package com.serheev.solutions.linkedlist;

/**
 * 2095. Delete the Middle Node of a Linked List
 * Task: You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes
 * the largest integer less than or equal to x.
 * <p>
 * For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
 *
 * @author Yurii Serheev
 */
public class DeleteMiddleNodeLinkedList {

    /**
     * <a href="https://medium.com/@arifimran5/fast-and-slow-pointer-pattern-in-linked-list-43647869ac99">Fast and Slow Pointers algorithm</a>
     * <p>
     * Time complexity: O(N)
     * Space complexity: O(1)
     *
     * @param head ListNode
     * @return ListNode
     */
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        prev.next = slow.next;

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
        // Input: head = [1,3,4,7,1,2,6]
        // Output: [1,3,4,1,2,6]
        ListNode node7 = new ListNode(6);
        ListNode node6 = new ListNode(2, node7);
        ListNode node5 = new ListNode(1, node6);
        ListNode node4 = new ListNode(7, node5);
        ListNode node3 = new ListNode(4, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(1, node2);

        System.out.print("Input: ");
        printList(node1);

        ListNode result = new DeleteMiddleNodeLinkedList().deleteMiddle(node1);

        System.out.print("\nOutput: ");
        printList(result);
    }
}
