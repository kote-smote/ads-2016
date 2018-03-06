package dataStructures;

import java.util.Iterator;
import adt.List;

public class SinglyLinkedList<E> implements List<E> {

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void addHead(E e) {

	}

	@Override
	public void append(E e) {

	}

	@Override
	public void add(int pos, E e) {

	}

	@Override
	public boolean deleteFirst() {
		return false;
	}

	@Override
	public boolean deleteLast() {
		return false;
	}

	@Override
	public void deleteAtIndex(int index) {

	}

	@Override
	public void deleteElement(E e) {

	}

	@Override
	public E getFirst() {
		return null;
	}

	@Override
	public E getLast() {
		return null;
	}

	@Override
	public E get(int pos) {
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	/* ====== PRIVATE MEMBERS ===== */

	private class Node<T> {
		T info;
		Node<T> next;

		public Node(T data, Node<T> next) {
			this.info = data;
			this.next = next;
		}

		public Node(T info) {
			this.info = info;
			this.next = null;
		}
	}
}
//
//public class SinglyLinkedList<E> implements List<E> {
//	private SLNode<E> head;
//	private SLNode<E> tail;
//	private int size;
//
//	public SinglyLinkedList() {
//		head = null;
//		tail = null;
//		size = 0;
//	}
//
//	public SinglyLinkedList(E[] array) {
//		for (E el : array)
//			append(el);
//	}
//
//	public SinglyLinkedList(SinglyLinkedList<? extends E> ls) {
//		Iterator<? extends E> it = ls.iterator();
//        while (it.hasNext()) {
//        	append(it.next());
//        }
//	}
//
//	public int size() {
//		return size;
//	}
//
//	public boolean isEmpty() {
//		return head == null;
//	}
//
//	public void addHead(E element) {
//		SLNode<E> newNode = new SLNode<E>(element, head);
//		if (isEmpty())
//			tail = newNode;
//		head = newNode;
//		size++;
//	}
//
//	public void append(E element) {
//		if (isEmpty()) {
//			addFirst(element);
//			return;
//		}
//		SLNode<E> newNode = new SLNode<E>(element, null);
//		tail.link = newNode;
//		tail = newNode;
//		size++;
//	}
//
//	public void add(int pos, E element) {
//		if (pos == 0)
//			addFirst(element);
//		SLNode<E> prev = findPredecessorFromPosition(pos);
//		SLNode<E> newNode = new SLNode<E>(element, prev.link);
//		prev.link = newNode;
//		size++;
//		return;
//	}
//
//	public E deleteFirst() {
//		if (isEmpty())
//			return null;
//		E toBeDeleted = head.data;
//		head = head.link;
//		size--;
//		return toBeDeleted;
//	}
//
//	public E deleteLast() {
//		if (isEmpty())
//			return null;
//		E toBeDeleted = tail.data;
//		SLNode<E> prev = findNodeFromPosition(size - 2);
//		tail = prev;
//		tail.link = null;
//		size--;
//		return toBeDeleted;
//	}
//
//	public E deleteAtPosition(int pos) {
//		if (pos < 0 || pos >= size || isEmpty())
//			throw new IndexOutOfBoundsException();
//		if (pos == 0)
//			return deleteFirst();
//		if (pos == size - 1)
//			return deleteLast();
//		SLNode<E> prev = findPredecessorFromPosition(pos);
//		E toBeDeleted = prev.link.data;
//		prev.link = prev.link.link;
//		size--;
//		return toBeDeleted;
//	}
//
//	public void deleteElement(E element) {
//		if (head.data.equals(element)) {
//			return deleteFirst();
//		}
//		SLNode<E> prev = findPredecessorFromElement(element);
//		if (prev == null)
//			return null;
//		if (prev.link == tail)
//			return deleteLast();
//		E toBeDeleted = prev.link.data;
//		prev.link = prev.link.link;
//		size--;
//		return toBeDeleted;
//	}
//
//	public E getFirst() {
//		if (isEmpty())
//			throw new IndexOutOfBoundsException();
//		return head.data;
//	}
//
//	public E getLast() {
//		if (isEmpty())
//			throw new IndexOutOfBoundsException();
//		return tail.data;
//	}
//
//	public E get(int pos) {
//		if (pos < 0 || pos >= size || isEmpty())
//			throw new IndexOutOfBoundsException();
//		if (pos == 0)
//			return getFirst();
//		if (pos == size - 1)
//			return getLast();
//		return findNodeFromPosition(pos).data;
//	}
//
//	@Override
//	public String toString() {
//		if (isEmpty())
//			return "[]";
//		StringBuilder result = new StringBuilder("[");
//		SLNode<E> tmp = head;
//		while (tmp != null) {
//			result.append(tmp.data);
//			result.append(", ");
//			tmp = tmp.link;
//		}
//		result.delete(result.length() - 2, result.length());
//		result.append("]");
//		return result.toString();
//	}
//
//	public Iterator<E> iterator() {
//		return new Iterator<E>() {
//			SLNode<E> tmp = head;
//
//			public E next() {
//				E data = tmp.data;
//				tmp = tmp.link;
//				return data;
//			}
//
//			public boolean hasNext() {
//				return tmp != null;
//			}
//
//			public void remove() {
//				throw new UnsupportedOperationException();
//			}
//		};
//	}
//
//	public boolean insertBefore(E key, E toInsert) {
//		SLNode<E> prev = findPredecessorFromElement(key);
//		if (prev == null)
//			return false;
//		SLNode<E> newNode = new SLNode<E>(toInsert, prev.link);
//		prev.link = newNode;
//		size++;
//		return true;
//	}
//
//	public boolean insertAfter(E key, E toInsert) {
//		SLNode<E> curr = findNodeFromElement(key);
//		if (curr == null)
//			return false;
//		SLNode<E> newNode = new SLNode<E>(toInsert, curr.link);
//		curr.link = newNode;
//		size++;
//		return true;
//	}
//
//	/**
//	 * Reverses the list itself
//	 */
//	public void reverse() {
//		if (!isEmpty() && size != 1) {
//			tail = head;
//			SLNode<E> next;
//			SLNode<E> newSucc = null;
//			while (head != null) {
//				next = head.link;
//				head.link = newSucc;
//				newSucc = head;
//				head = next;
//			}
//			head = newSucc;
//		}
//	}
//
//	/**
//	 * Returns new list which is the reversed version of the
//	 * original list. The original list remains untouched.
//	 * @return SLList<E>
//	 */
//	public SinglyLinkedList<E> mirrorList() {
//		SinglyLinkedList<E> res = new SinglyLinkedList<E>();
//		Iterator<E> it = this.iterator();
//		while (it.hasNext())
//			res.addFirst(it.next());
//		return res;
//	}
//
//	/*
//	 * Private methods used locally
//	 */
//
//	private SLNode<E> findPredecessorFromPosition(int pos) {
//		if (pos <= 0 || pos >= size)
//			throw new IndexOutOfBoundsException();
//
//		SLNode<E> tmp = head;
//		int index = 0;
//		while (tmp.link.link != null && index != pos - 1) {
//			tmp = tmp.link;
//			index++;
//		}
//		return tmp;
//	}
//
//	private SLNode<E> findNodeFromPosition(int pos) {
//		if (pos == 0)
//			return head;
//		if (pos >= size || pos < 0)
//			throw new IndexOutOfBoundsException();
//		return findPredecessorFromPosition(pos).link;
//	}
//
//	private SLNode<E> findPredecessorFromElement(E element) {
//		SLNode<E> tmp = head;
//		while (tmp.link != null) {
//			if (tmp.link.data.equals(element))
//				return tmp;
//			tmp = tmp.link;
//		}
//		return null;
//	}
//
//	private SLNode<E> findNodeFromElement(E element) {
//		if (head.data.equals(element))
//			return head;
//		return findPredecessorFromElement(element).link;
//	}
//}