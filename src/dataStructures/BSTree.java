package dataStructures;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

import exceptions.EmptyTreeException;

/**
 * Created by martin on 12/9/16.
 */
public class BSTree<E extends Comparable<E>> {
    private BTNode root;
    private List<E> sortedElements;
    private int size;

    public BSTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void makeEmpty() {
        root = null;
        size = 0;
    }

    public List<E> getSorted() {
        sortedElements = new ArrayList<E>();
        inorder(root);
        return sortedElements;
    }

    private void inorder(BTNode node) {
        if (node != null) {
            inorder(node.left);
            sortedElements.add(node.data);
            inorder(node.right);
        }
    }

    public void insert(E elem) {
        root = insert(elem, root);
        ++size;
    }

    private BTNode insert(E elem, BTNode node) {
        if (node == null)
            node = new BTNode(elem);
        else if (elem.compareTo(node.data) < 0)
            node.left = insert(elem, node.left);
        else if (elem.compareTo(node.data) > 0)
            node.right = insert(elem, node.right);
        return node; // returns the root at the end
    }

//    // insert operation - iterative approach
//    public void insert(E elem) {
//
//        BTNode tmp = null;
//        BTNode traverse = root;
//
//        while (traverse != null) {
//            tmp = traverse;
//            if (elem.compareTo(traverse.data) < 0
//                traverse = traverse.left;
//            else if (elem.compareTo(traverse.data) > 0)
//                traverse = traverse.right;
//
//        }
//
//        if (tmp != null) {
//            if (elem.compareTo(tmp.data) < 0)
//                tmp.left = new BTNode(elem);
//            else if (elem.compareTo(tmp.data) > 0)
//                tmp.right = new BTNode(elem);
//        } else
//            root = new BTNode(elem);
//    }

    public boolean remove(E elem) {
        if (isEmpty())
            throw new EmptyTreeException();
        try {
            remove(elem, root);
            --size;
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public int height() {
        return getHeight(root);
    }

    public int heightOf(E elem) {
        BTNode node = findNode(root, elem);
        return getHeight(node);
    }

    // Height of a node is the length of the path
    // from the node to the deepest node in the tree
    private int getHeight(BTNode node) {
        if (node == null) {
            return -1;
        }

        int left = getHeight(node.left);
        int right = getHeight(node.right);

        if (left > right) {
            return left + 1;
        } else {
            return right + 1;
        }
    }

    public int getCountAtLevel(int level) {
        return getCountAtLevel(root, 0, level);
    }

    private int getCountAtLevel(BTNode node, int curr, int desired) {
        if (node == null)
            return 0;
        if (curr == desired) {
            return 1;
        }
        return getCountAtLevel(node.left, curr + 1, desired)
                + getCountAtLevel(node.right, curr + 1, desired);
    }

    private BTNode findNode(BTNode node, E elem) {
        if (node == null)
            return null;
        if (elem.compareTo(node.data) < 0)
            return findNode(node.left, elem);
        if (elem.compareTo(node.data) > 0)
            return findNode(node.right, elem);
        else
            return node;
    }

    private BTNode remove(E elem, BTNode node) {
        if (node == null)
            throw new NoSuchElementException(elem.toString());
        if (elem.compareTo(node.data) < 0)
            node.left = remove(elem, node.left);
        else if (elem.compareTo(node.data) > 0)
            node.right = remove(elem, node.right);
        else { // element that is to be deleted is found
            // element has two children
            if (node.left != null && node.right != null) {
                node.data = getMax(node.right);
                node.right = remove(elem, node.right);
            } else if (node.left != null) { // element has only the left child
                return node.left;
            } else // element has no children or has only right child
                return node.right;
        }
        return node;
    }

    public boolean contains(E elem) {
        if (isEmpty())
            throw new EmptyTreeException();
        return contains(elem, root);
    }

    private boolean contains(E elem, BTNode node) {
        if (node == null)
            return false;
        else if (elem.compareTo(node.data) < 0)
            return contains(elem, node.left);
        else if (elem.compareTo(node.data) > 0)
            return contains(elem, node.right);
        else
            return true;
    }

    public E getMin() {
        return getMin(root);
    }

    private E getMin(BTNode node) {
        if (isEmpty())
            throw new EmptyTreeException();
        BTNode tmp = node;
        while (tmp.left != null) tmp = tmp.left;
        return tmp.data;
    }

    public E getMax() {
        return getMax(root);
    }

    private E getMax(BTNode node) {
        if (isEmpty())
            throw new EmptyTreeException();
        BTNode tmp = node;
        while (tmp.right != null) tmp = tmp.right;
        return tmp.data;
    }


    /**
     * Private class defining the data element of the
     * Binary Search Tree
     */
    private class BTNode {
        E data;
        BTNode left;
        BTNode right;

        public BTNode(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        public BTNode(E data, BTNode left, BTNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BSTree<Integer> bst = new BSTree<>();
        Set<Integer> set = new TreeSet<>();

        /*
        bst.insert(6);
        bst.insert(2);
        bst.insert(8);
        bst.insert(1);
        bst.insert(4);
        bst.insert(3);

        System.out.println("max: " + bst.getMax());
        System.out.println("min: " + bst.getMin());

        bst.remove(4);
        bst.insert(5);

        System.out.println(bst.getSorted());
        System.out.println(bst.contains(6));
        System.out.println(bst.contains(7));
        */



        /*
         * === Test contains method ===
         */


        System.out.println();

        /*
         * === Test sort method ===
         */

    }
}
