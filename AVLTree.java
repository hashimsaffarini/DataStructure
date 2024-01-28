package datastructureclasses;

public class AVLTree {

    private class AVLNode {

        int value;
        AVLNode left, right;
        int height;

        AVLNode(int value) {
            this.value = value;
            this.height = 1;
        }
    }

    private AVLNode root;

    private int height(AVLNode N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    private int getBalance(AVLNode N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    private AVLNode insert(AVLNode node, int value) {
        if (node == null) {
            return new AVLNode(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && value < node.left.value) {
            return rightRotate(node);
        }

        if (balance < -1 && value > node.right.value) {
            return leftRotate(node);
        }

        if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void preOrder(AVLNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void preOrderTraversal() {
        preOrder(root);
    }
}
