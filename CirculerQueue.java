package datastructureclasses;

public class CirculerQueue<T> {

    private final T[] arr;
    private int front, rear, size;

    public CirculerQueue(int size) {
        this.size = size;
        arr = (T[]) new Object[size];
        front = rear = -1;
    }

    public void enqueue(T val) {
        if (!isFull()) {
            if (isEmpty()) {
                front = 0;
            }
            rear = (rear + 1) % size;
            arr[rear] = val;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            T val = arr[front];
            if (rear == front) {
                rear = front = -1;
            } else {
                front = (front + 1) % size;
            }
            return val;
        }
    }

    public T rear() {
        if (isEmpty()) {
            return null;
        } else {
            return arr[rear];
        }
    }

    public T front() {
        if (isEmpty()) {
            return null;
        } else {
            return arr[front];
        }
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (rear + 1) % size == front;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder s = new StringBuilder("[");
        for (int i = front;; i = (i + 1) % size) {
            s.append(arr[i]);
            if (i == rear) {
                break;
            }
            s.append(", ");
        }
        s.append("]");
        return s.toString();
    }
}
