package datastructureclasses;

public class BST {

    public static class Node {

        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }
    Node root;

    public Node add(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }
        if (val > root.val) {
            root.right = add(root.right, val);
        } else if (val < root.val) {
            root.left = add(root.left, val);
        }
        return root;
    }

    public void add(int val) {
        root = add(root, val);
    }

    public String print(Node root, String s) {
        if (root == null) {
            return s;
        }
        //Infix
        s = print(root.left, s);
        s += (root.val + " ");
        s = print(root.right, s);
        return s;
    }

    public void remove(int val) {
        root = remove(root, val);
    }

    public Node remove(Node root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val > val) {
            root.left = remove(root.left, val);
        } else if (root.val < val) {
            root.right = remove(root.right, val);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            root.val = MinVal(root.right);
            root.right = remove(root.right, root.val);
        }
        return root;
    }

    public int MinVal(Node root) {
        int min = root.val;
        while (root.left != null) {
            min = root.left.val;
            root = root.left;
        }
        return min;
    }

    @Override
    public String toString() {
        return print(root, "");
    }
}
