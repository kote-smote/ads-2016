package dataStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by martin on 12/10/16.
 */
public class ThreadedBinaryTree<E> {
    private Node head;

    public ThreadedBinaryTree() {
        head = new Node(null);
        // by definition, if the tree is empty:
        head.left = head;
        head.lTag = false;
        // head's right link always points to itself
        head.right = head;
        head.rTag = true;
    }

    public Node makeRoot(E elem) {
        Node root = new Node(elem);
        head.left = root;
        head.lTag = true;
        root.left = head;
        root.right = head;
        return root;
    }

    public Node root() {
        if (head.left == head)
            return null;
        return head.left;
    }

    public Node addChild(Node parent, int where, E elem) {
        Node child = new Node(elem);

        if (where == 1) {
            if (parent.lTag == true) // left child already exists
                return null;
            child.left = parent.left;
            child.right = parent;
            parent.left = child;
            parent.lTag = true;
        } else {
            if (parent.rTag == true) // right child already exists
                return null;
            child.right = parent.right;
            child.left = parent;
            parent.right = child;
            parent.rTag = true;
        }
        return child;
    }

    private Node successorInOrder(Node node) {
        if (node.rTag == false)
            return node.right;

        Node tmp = node.right;
        while (tmp.lTag == true)
            tmp = tmp.left;
        return tmp;
    }

    public List<E> inOrderTraversal() {
        Node tmp = this.root();

        if (tmp == null)
            return Collections.<E>emptyList();

        List<E> resList = new ArrayList<>();

        while (tmp.lTag == true)
            tmp = tmp.left;

        while (tmp != head) {
            resList.add(tmp.data);
            tmp = successorInOrder(tmp);
        }
        return resList;
    }

    private class Node {
        public E data;
        public Node left;
        public Node right;
        boolean lTag; // true - link, false - thread
        boolean rTag;

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
            lTag = false;
            rTag = false;
        }
    }
}
