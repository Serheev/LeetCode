package com.serheev.solutions.data_structures.stack;

import java.util.EmptyStackException;

/**
 * The {@code Stack} class represents a last-in-first-out (LIFO) stack of objects.
 * This is a fast and simple LinkedList implementation of Stack.
 *
 * @author Yurii Serheev
 */
public class StackLinkedListImplement<T> {
    private Node<T> top;
    private int size;

    public StackLinkedListImplement() {
        this.top = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T value = top.data;
        top = top.next;
        size--;

        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return top.data;
    }

    public int size() {
        return size;
    }

    public void reverse() {
        if (isEmpty()) {
            return;
        }

        Node<T> previous = null;
        Node<T> current = top;
        Node<T> temp;

        while (current != null) {
            temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }

        top = previous;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        StackLinkedListImplement<Integer> stack = new StackLinkedListImplement<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);

        System.out.println(stack.peek());
        System.out.println();

        stack.push(11);

        System.out.println(stack.peek());
        System.out.println();

        stack.reverse();

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println();
        System.out.println(stack.isEmpty());

        stack.peek();
    }
}
