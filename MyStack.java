package datastructureclasses;

import java.util.EmptyStackException;

public class MyStack<T> {

    private T[] arr;
    private int size;

    public MyStack() {
        arr = (T[]) new Object[5];
        size = 0;
    }

    public void push(T val) {
        if (size == arr.length) {
            expandArray();
        }
        arr[size++] = val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void expandArray() {
        T[] newArray = (T[]) new Object[arr.length * 2];
        System.arraycopy(arr, 0, newArray, 0, size);
        arr = newArray;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return arr[size - 1];
    }

    public int size() {
        return size;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return arr[--size];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            s.append(arr[i]);
            if (i != size - 1) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }

    public static MyStack<Integer> copy(MyStack<Integer> stack) {
        MyStack<Integer> temp = new MyStack<>();
        MyStack<Integer> copied = new MyStack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        while (!temp.isEmpty()) {
            Integer value = temp.pop();
            stack.push(value);
            copied.push(value);
        }
        return copied;
    }
}
