import java.util.List;
import java.util.ArrayList;
import adt.Stack;
import dataStructures.LinkedStack;

public class BinaryTree<E> {
    BTNode<E> root;
    private List<E> resList;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(E element) {
        root = new BTNode<E>(element);
    }

    public void makeRoot(E element) {
        root = new BTNode<E>(element);
    }

    public BTNode<E> addChild(BTNode<E> node, int where, E element) {
        BTNode<E> child = new BTNode<>(element);

        if (where == 1) {
            if (node.left != null)
                return null;
            node.left = child;
        } else {
            if (node.right != null)
                return null;
            node.right = child;
        }
        return child;
    }

    /* RECURSIVE TRAVERSALS */

    public List<E> preOrderRecursive() {
        resList = new ArrayList<>();
        preOrderR(root);
        return new ArrayList<>(resList);
    }

    private void preOrderR(BTNode<E> node) {
        if (node != null) {
            resList.add(node.data);
            preOrderR(node.left);
            preOrderR(node.right);
        }
    }

    public List<E> inOrderRecursive() {
        resList = new ArrayList<>();
        inOrderR(root);
        return new ArrayList<>(resList);
    }

    private void inOrderR(BTNode<E> node) {
        if (node != null) {
            inOrderR(node.left);
            resList.add(node.data);
            inOrderR(node.right);
        }
    }

    public List<E> postOrderRecursive() {
        resList = new ArrayList<>();
        postorderR(root);
        return new ArrayList<>(resList);
    }

    private void postorderR(BTNode<E> node) {
        if (node != null) {
            postorderR(node.left);
            postorderR(node.right);
            resList.add(node.data);
        }
    }

    /* ITERATIVE TRAVERSALS */

    public List<E> preOrderIterative() {
        Stack<BTNode<E>> stack = new LinkedStack<>();
        List<E> list = new ArrayList<>();
        BTNode<E> tmp = root;

        // Check for empty tree
        if (tmp == null)
            return list;

        while (true) {

            while (tmp != null) {
                list.add(tmp.data);
                if (tmp.right != null)
                    stack.push(tmp.right);
                tmp = tmp.left;
            }

            if (stack.isEmpty())
                break;

            tmp = stack.pop();
        }
        return list;
    }

    public List<E> inOrderIterative() {
        Stack<BTNode<E>> stack = new LinkedStack<>();
        List<E> list = new ArrayList<E>();
        BTNode<E> tmp = root;

        // Check for empty tree
        if (tmp == null)
            return list;

        while (true) {

            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }

            if (stack.isEmpty())
                break;

            BTNode<E> node = stack.pop();
            list.add(node.data);
            tmp = node.right;
        }
        return list;
    }

    public List<E> postOrderIterative() {
        Stack<BTNode<E>> stack = new LinkedStack<>();
        List<E> list = new ArrayList<>();
        BTNode<E> tmp = root;

        // Check for empty tree
        if (tmp == null)
            return list;

        do {

            while (tmp != null) {
                if (tmp.right != null)
                    stack.push(tmp.right);
                stack.push(tmp);
                tmp = tmp.left;
            }

            BTNode<E> node = stack.pop();
            if (node.right != null && node.right.equals(stack.peek())) {
                tmp = node.right;
                stack.pop();
                stack.push(node);
            } else {
                list.add(node.data);
            }

        } while (!stack.isEmpty());
        return list;
    }

    public static void main(String[] args) {
        BinaryTree<Integer> bt = new BinaryTree<>(15);

        BTNode<Integer> a1 = bt.addChild(bt.root, 1, 6);
        BTNode<Integer> b1 = bt.addChild(bt.root, 2, 18);

        BTNode<Integer> a2 = bt.addChild(a1, 1, 3);
        BTNode<Integer> b2 = bt.addChild(a1, 2, 7);
        BTNode<Integer> c2 = bt.addChild(b1, 1, 17);
        BTNode<Integer> d2 = bt.addChild(b1, 2, 20);

        BTNode<Integer> a3 = bt.addChild(a2, 1, 2);
        BTNode<Integer> b3 = bt.addChild(a2, 2, 4);
        BTNode<Integer> c3 = bt.addChild(b2, 2, 13);

        BTNode<Integer> a4 = bt.addChild(c3, 1, 9);

        System.out.println("Recursive Traversals:");
        System.out.format("-%-9s: %s\n", "Preorder", bt.preOrderRecursive());
        System.out.format("-%-9s: %s\n", "Inorder", bt.inOrderRecursive());
        System.out.format("-%-9s: %s\n", "Postorder", bt.postOrderRecursive());

        System.out.println();

        System.out.println("Iterative (Non-Recursive) Traversals:");
        System.out.format("-%-9s: %s\n", "Preorder", bt.preOrderIterative());
        System.out.format("-%-9s: %s\n", "Inorder", bt.inOrderIterative());
        System.out.format("-%-9s: %s\n", "Postorder", bt.postOrderIterative());
    }
}

class BTNode<E> {
    public E data;
    public BTNode<E> left;
    public BTNode<E> right;

    public BTNode(E data) {
        this.data = data;
        left = right = null;
    }

    public BTNode(E data, BTNode<E> left, BTNode<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

