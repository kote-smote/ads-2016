package dataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import adt.List;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
    private Node head, tail;
    private int size;

    public DoublyLinkedList() {
        head = tail = null;
        size = 0;
    }

    public DoublyLinkedList(E[] array) { 
        for (E el : array) append(el); 
    }

    public DoublyLinkedList(DoublyLinkedList<? extends E> ls) {
        
        Iterator<? extends E> it = ls.iterator();
        while (it.hasNext()) {
            append(it.next());
        }
        
    }

    public boolean isEmpty() { return this.head == null; }

    public int size() { return size; }

    public void addHead(E elem) {
        
        if (elem == null)
            throw new NullPointerException("argument passed is null");
        
        Node newNode = new Node(elem, null, head);
        
        if (this.isEmpty()) this.tail = newNode;
        else this.head.prev = newNode;
        head = newNode;
        size++;
    }

    public void append(E elem) {

        if (elem == null)
            throw new NullPointerException("argument passed is null");

        if (this.isEmpty()) {
            addHead(elem);
            return;
        }

        Node newNode = new Node(elem, tail, null);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public E getFirst() {

        if (this.isEmpty())
            throw new IndexOutOfBoundsException();

        return this.head.info;
    }

    public E getLast() {

        if (this.isEmpty())
            throw new IndexOutOfBoundsException();

        return this.tail.info;
    }

    public E get(int index) {

        if (this.indexNotValid(index))
            throw new IndexOutOfBoundsException();

        Node node = this.nodeAt(index);
        return node.info;
    }

    public boolean deleteFirst() {

        if (this.isEmpty())
            return false;

        this.unlink(this.head);
        size--;
        return true;
    }

    public boolean deleteLast() {

        if (this.isEmpty())
            return false;

        this.unlink(this.tail);
        size--;
        return true;
    }

    public void deleteAtIndex(int index) {

        if (this.indexNotValid(index) || this.isEmpty())
            throw new IndexOutOfBoundsException();

        Node node = this.nodeAt(index);
        this.unlink(node);
        size--;
    }

    public void deleteElement(E elem) {

        if (elem == null)
            throw new NullPointerException("argument passed is null");

        Node node = this.nodeOf(elem);
        if (node == null) throw new NoSuchElementException();
        this.unlink(node);
        size--;
    }

    public void add(int index, E elem) {

        if (this.indexNotValid(index))
            throw new IndexOutOfBoundsException();

        if (index == 0)
            this.addHead(elem);
        else if (index == size)
            this.append(elem);
        else {
            Node rightNode = nodeAt(index);
            Node node = new Node(elem);
            this.link(node, rightNode);
            size++;
        }
    }

    public int indexOf(E elem) {

        int index = 0;

        for (E e : this) {
            if (elem.equals(e))
                return index;
            index++;
        }

        return -1;
    }

    public Iterator<E> iterator() {

        // Anonymous inner class
        return new Iterator<E>() {
            Node tmp = head;

            public boolean hasNext() {
                return tmp != null;
            }

            public E next() {
                E info = tmp.info;
                tmp = tmp.next;
                return info;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Iterator<Node> nodeIterator() {

        // Anonymous inner class
        return new Iterator<Node>() {
            Node it = head;

            public boolean hasNext() {
                return it != null;
            }

            public Node next() {
                Node tmp = it;
                it = it.next;
                return tmp;
            }

            public void remove() {
                if (it.prev == null)
                    throw new IllegalStateException();
                unlink(it.prev);
            }
        };
    }

    @Override
    public String toString() {

        if (this.isEmpty())
            return "[]";
        StringBuilder result = new StringBuilder("[");
        for (E elem : this) {
            result.append(elem);
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }

    private boolean indexNotValid(int index) {
        return index < 0 || index >= size;
    }

    private Node nodeAt(int index) {

        if (index == 0) return this.head;
        if (index == this.size - 1) return this.tail;

        Node node = null;
        Iterator<Node> it = this.nodeIterator();

        for (int i = 0; i < index + 1; i++)
            node = it.next();

        return node;
    }

    private Node nodeOf(E elem) {

        Iterator<Node> it = this.nodeIterator();
        Node node;
        while (it.hasNext()) {
            node = it.next();
            if (node.info.equals(elem))
                return node;
        }
        return null;
    }

    private void unlink(Node node) {

        if (node == head) {
            if (head == tail)
                head = tail = null;
            else {
                head = head.next;
                head.prev = null;
            }
        } else if (node == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private void link(Node node, Node rightNode) {

        node.prev = rightNode.prev;
        node.next = rightNode;
        rightNode.prev = node;
        node.prev.next = node;
    }

    private class Node {

        public E info;
        public Node next;
        public Node prev;

        public Node(E info) {
            this.info = info;
            next = prev = null;
        }

        public Node(E info, Node prev, Node next) {
            this.info = info;
            this.next = next;
            this.prev = prev;
        }
    }

}