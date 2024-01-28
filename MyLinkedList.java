package datastructureclasses;

public class MyLinkedList<T> {

    class Node<T> {

        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T val) {
        Node<T> newNode = new Node<>(val);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void add(int index, T val) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            addFirst(val);
        } else if (index == size) {
            add(val);
        } else {
            Node<T> newNode = new Node<>(val);
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public T removeFirst() {
        if (head == null) {
            throw new ArrayIndexOutOfBoundsException("List is empty");
        }
        T old = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return old;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            return removeFirst();
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            T old = current.next.value;
            if (index == size - 1) {
                tail = current;
                current.next = null;
            } else {
                current.next = current.next.next;
            }
            size--;
            return old;
        }
    }

    public void addFirst(T val) {
        Node<T> newNode = new Node<>(val);
        newNode.next = head;
        head = newNode;
        if (size == 0) {
            tail = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public int size() {
        return size;
    }

    public T getFirst() {
        if (head == null) {
            throw new ArrayIndexOutOfBoundsException("List is empty");
        }
        return head.value;
    }

    public T getLast() {
        if (tail == null) {
            throw new ArrayIndexOutOfBoundsException("List is empty");
        }
        return tail.value;
    }

    public String print() {
        StringBuilder s = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            s.append(current.value).append(" ");
            current = current.next;
        }
        return s.toString().trim();
    }

    @Override
    public String toString() {
        return print();
    }
}
