/**
 * Algoritmi i Podatocni Strukturi - AV 02 - Zadaca 5
 * 
 * Da se napise programa za proizvolno dvojno povrzana lista vo 
 * koja ke se isfrlat site jazli sto se povtoruvaat. 
 * Dopolnitelno, sekoj jazol na ovaa lista pokraj objektot, 
 * sodrzi i dopolnitelna informacija za brojot na povtoruvanja
 * na dadeniot jazol.
 * Da se napise soodvetna klasa za vakov tip na jazol i da se 
 * implementira metoda koja ke gi isfrli povekekratnite pojavuvanja
 * na edna vrednost i ke ostavi samo eden jazol so ovaa vrednost, 
 * vo koj ke se zapise kolku pati prvobitno se pojavila vrednosta
 * vo listata.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RemoveDuplicates {
	
	public static void main(String[] args) {
		DLList<Integer> dll = new DLList<Integer>(
			new Integer[]{3, 2, 3, 4, 2, 3, 5, 5, 1, 5, 4});
		System.out.println(dll);
		dll.removeDuplicates();
		System.out.println(dll);
		for (Integer i : dll)
			System.out.format("brojot %d se pojavi %d pati\n", 
					i, dll.getReps(i));
	}
	
}

class CountableNode<E> {
	protected E data;
	protected CountableNode<E> next;
	protected CountableNode<E> prev;
	protected int reps;
	
	public CountableNode(E data) {
		this.data = data;
		next = prev = null;
		reps = 1;
	}

	public CountableNode(E data, CountableNode<E> prev, CountableNode<E> next) {
		this.data = data;
		this.next = next;
		this.prev = prev;
		reps = 1;
	}	
}

class DLList<E> implements Iterable<E> {
	private CountableNode<E> head;
	private CountableNode<E> tail;
	private int size;
	
	public DLList() {
		head = tail = null;
		size = 0;
	}
	
	public DLList(E[] array) {
		for (E el : array)
			append(el);
	}
	
	public DLList(DLList<? extends E> ls) {
		for (int i = 0; i < ls.size(); i++)
			append(ls.get(i));
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		return size;
	}
	
	public void addFirst(E element) {
		CountableNode<E> newNode = new CountableNode<E>(element, null, head);
		if (isEmpty())
			tail = newNode;
		else 
			head.prev = newNode;
		head = newNode;
		size++;
	}
	
	public void append(E element) {
		if (isEmpty()) {
			addFirst(element);
			return;
		}
		CountableNode<E> newNode = new CountableNode<E>(element, tail, null);
		tail.next = newNode;
		tail = newNode;
		size++;
	}
	
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

	public E get(int index) {
		return getNodeFromPosition(index).data;
	}
	
	public E deleteFirst() {
		if (isEmpty())
			return null;
		E deleted = head.data;
		if (head == tail)
			head = tail = null;
		else {
			head = head.next;
			head.prev = null;
		}
		size--;
		return deleted;
	}
	
	public E deleteLast() {
		if (isEmpty())
			return null;
		E deleted = tail.data;
		if (head == tail)
			head = tail = null;
		else {
			tail = tail.prev;
			tail.next = null;
		}
		size--;
		return deleted;
	}
	
	public E deleteFromPosition(int index) {
		return deleteNode(getNodeFromPosition(index));
				
	}
	
	public E deleteElement(E element) {
		return deleteNode(getNodeFromElement(element));
	}
	
	public void add(int pos, E element) {
		if (pos < 0 || pos > size)
			throw new IndexOutOfBoundsException();
		if (pos == 0) {
			addFirst(element);
			return;
		}
		if (pos == size) {
			append(element);
			return;
		}
		CountableNode<E> curr = getNodeFromPosition(pos);
		insertNode(curr, new CountableNode<E>(element));
		size++;
	}
	
//	public void insertAfter(E key, E elem) {
//		CountableNode<E> curr = getNodeFromElement(key).next;
//		if (curr == null) {
//			append(elem);
//			return;
//		}
//		insertNode(curr, new CountableNode<E>(elem));
//		size++;
//	}
//	
//	public void insertBefore(E key, E elem) {
//		CountableNode<E> curr = getNodeFromElement(key);
//		if (curr == head) {
//			addFirst(elem);
//			return;
//		}
//		insertNode(curr, new CountableNode<E>(elem));
//		size++;
//	}
	
//	public int indexOf(E element) {
//		CountableNode<E> tmp = head;
//		int index = 0;
//		while (tmp != null) {
//			if (tmp.data.equals(element))
//				return index;
//			tmp = tmp.next;
//			index++;
//		} 
//		return -1;
//	}
	
	public void removeDuplicates() {
		CountableNode<E> tmp1 = head;
		CountableNode<E> tmp2;
		
		while (tmp1 != null) {
			tmp2 = tmp1.next;
			while (tmp2 != null) {
				if (tmp1.data.equals(tmp2.data)) {
					deleteNode(tmp2);
					tmp1.reps++;
				}
				tmp2 = tmp2.next;
			}
			tmp1 = tmp1.next;
		}		
	}
	
	public int getReps(E e) {
		return getNodeFromElement(e).reps;
	}
	
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			CountableNode<E> tmp = head;
			
			public boolean hasNext() {
				return tmp != null;
			}
			
			public E next() {
				E data = tmp.data;
				tmp = tmp.next;
				return data;
			}
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public String toString() {
		if (isEmpty())
			return "[]";
		StringBuilder result = new StringBuilder("[");
		CountableNode<E> tmp = head;
		while (tmp != null) {
			result.append(tmp.data);
			result.append(", ");
			tmp = tmp.next;
		}
		result.delete(result.length() - 2, result.length());
		result.append("]");
		return result.toString();		
	}
	
	private CountableNode<E> getNodeFromPosition(int pos) {
		if (pos < 0 || pos >= size || isEmpty())
			throw new IndexOutOfBoundsException();
		if (pos == 0)
			return head;
		if (pos == size - 1)
			return tail;
		int i = 1;
		CountableNode<E> tmp = head.next;
		while (tmp != null) {
			if (i == pos)
				return tmp;
			tmp = tmp.next;
			i++;
		}
		return tmp;
	}
	
	private CountableNode<E> getNodeFromElement(E element) {
		CountableNode<E> tmp = head;
		while (tmp != null) {
			if (tmp.data.equals(element))
				return tmp;
			tmp = tmp.next;
		} 
		throw new NoSuchElementException();
	}	
	
	private E deleteNode(CountableNode<E> toBeDeleted) {
		if (toBeDeleted == head)
			return deleteFirst();
		if (toBeDeleted == tail)
			return deleteLast();
		E deleted = toBeDeleted.data;
		toBeDeleted.prev.next = toBeDeleted.next;
		toBeDeleted.next.prev = toBeDeleted.prev;
		size--;
		return deleted;	
	}
	
	private void insertNode(CountableNode<E> curr, CountableNode<E> ins) {
		ins.prev = curr.prev;
		ins.next = curr;
		curr.prev = ins;
		ins.prev.next = ins;
	}
}
