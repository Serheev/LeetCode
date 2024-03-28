package com.serheev.solutions.stack;

import java.util.Arrays;
import java.util.EmptyStackException;


/**
 * The {@code Stack} class represents a last-in-first-out (LIFO) stack of objects.
 * This is a fast and simple Array implementation of Stack.
 *
 * @author Yurii Serheiev
 */
public class StackArrayImplement<T> {

    private static final int MIN_CAPACITY = 10;
    private T[] data;
    private int top;

    StackArrayImplement() {
        this.data = (T[]) new Object[MIN_CAPACITY];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(T value) {
        if (top == MIN_CAPACITY - 1) {
            grow();
        }

        data[++top] = value;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T element = data[top--];
        data[top + 1] = null; // after gc to do its work
        if (top <= data.length / 4) {
            shrink();
        }

        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return data[top];
    }

    private void grow() {
        data = Arrays.copyOf(data, MIN_CAPACITY * 2); // Simple variant of growing
    }

    private void shrink() {
        int newCapacity = Math.max(MIN_CAPACITY, data.length / 2);
        data = Arrays.copyOf(data, newCapacity);
    }

    public static void main(String[] args) {
        StackArrayImplement<Integer> stack = new StackArrayImplement<>();

        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(5);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(10);

        System.out.println(stack.peek());

        stack.push(11);

        System.out.println(stack.peek());

        stack.pop();

        System.out.println(Arrays.toString(stack.data));

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

        System.out.println(Arrays.toString(stack.data));
        System.out.println(stack.isEmpty());

        stack.peek();
    }
}
