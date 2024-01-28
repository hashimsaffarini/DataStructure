package datastructureclasses;

public class MyArrayList<T> {

    private T[] arr;
    private int size;

    public MyArrayList() {
        arr = (T[]) new Object[5];
        size = 0;
    }

    public void add(T val) {
        if (size == arr.length) {
            reSize();
        }
        arr[size++] = val;
    }

    public void add(int index, T val) {
        if (index >= 0 && index <= size) {
            if (size == arr.length) {
                reSize();
            }
            shiftRight(index);
            arr[index] = val;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void shiftRight(int index) {
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        size++;
    }

    public void shiftLeft(int index) {
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
    }

    public T set(int index, T val) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T old = arr[index];
        arr[index] = val;
        return old;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return arr[index];
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T old = arr[index];
        shiftLeft(index);
        return old;
    }

    public T remove(T value) {
        int index = indexOf(value);
        if (index != -1) {
            return remove(index);
        }
        return null;
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] != null && arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void reSize() {
        T[] newArray = (T[]) new Object[arr.length * 2];
        System.arraycopy(arr, 0, newArray, 0, size);
        arr = newArray;
    }

    public void clear() {
        arr = (T[]) new Object[5];
        size = 0;
    }

    public boolean equals(MyArrayList<T> other) {
        if (this.size != other.size) {
            return false;
        }
        for (int i = 0; i < other.size; i++) {
            T x = this.get(i);
            T y = other.get(i);
            if (x == null ? y != null : !x.equals(y)) {
                return false;
            }
        }
        return true;
    }

    public void addAll(MyArrayList<T> other) {
        if (other.size + this.size > arr.length) {
            reSize();
        }
        for (int i = 0; i < other.size; i++) {
            this.add(other.get(i));
        }
    }

    public int lastIndexOf(T value) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i] != null && arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public void removeAll(T value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] != null && arr[i].equals(value)) {
                remove(i);
                i--;
            }
        }
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
}
