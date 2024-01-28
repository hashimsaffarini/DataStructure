package datastructureclasses;

import java.util.LinkedList;

public class MyHashMap<K, V> {

    private class HashNode {

        K key;
        V value;

        HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<HashNode>[] array;
    private int arraySize;
    private int nodeCount;

    public MyHashMap() {
        this.arraySize = 9; 
        array = new LinkedList[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = new LinkedList<>();
        }
    }

    private int hashFunction(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % arraySize;
    }

    private int searchIndex(K key) {
        int index = hashFunction(key);
        LinkedList<HashNode> bucket = array[index];
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key.equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public void put(K key, V value) {
        int index = hashFunction(key);
        int nodeIndex = searchIndex(key);
        if (nodeIndex == -1) {
            array[index].add(new HashNode(key, value));
            nodeCount++;
        } else {
            array[index].get(nodeIndex).value = value;
        }

        if (loadFactorExceeded()) {
            rehash();
        }
    }

    private boolean loadFactorExceeded() {
        double loadFactor = (double) nodeCount / arraySize;
        return loadFactor > 0.75;
    }

    private void rehash() {
        LinkedList<HashNode>[] oldArray = array;
        int oldSize = arraySize;
        arraySize *= 2;
        array = new LinkedList[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = new LinkedList<>();
        }

        for (LinkedList<HashNode> bucket : oldArray) {
            for (HashNode node : bucket) {
                put(node.key, node.value);
            }
        }
    }

    public V get(K key) {
        int index = hashFunction(key);
        int nodeIndex = searchIndex(key);
        if (nodeIndex == -1) {
            return null;
        } else {
            return array[index].get(nodeIndex).value;
        }
    }

    public V remove(K key) {
        int index = hashFunction(key);
        int nodeIndex = searchIndex(key);
        if (nodeIndex == -1) {
            return null;
        } else {
            HashNode removedNode = array[index].remove(nodeIndex);
            nodeCount--;
            return removedNode.value;
        }
    }

    public boolean containsKey(K key) {
        return searchIndex(key) != -1;
    }

    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return value != null ? value : defaultValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (LinkedList<HashNode> bucket : array) {
            for (HashNode node : bucket) {
                sb.append(node.key).append("=").append(node.value).append(", ");
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2); 
        }
        sb.append("}");
        return sb.toString();
    }
}
