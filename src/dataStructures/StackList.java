package dataStructures;

import java.util.EmptyStackException;

import adt.Stack;

public class StackList<E> implements Stack<E> {
	private SinglyLinkedList<E> storage;
	
	public StackList() {
		storage = new SinglyLinkedList<>();
	}
	
	@Override
	public E push(E e) {
		storage.addHead(e);
		return e;
	}

	@Override
	public E pop() {
		E top = this.storage.getLast();
		this.storage.deleteFirst();
		return top;
	}

	@Override
	public E peek() {
		try {
			return storage.getFirst();
		} catch (IndexOutOfBoundsException e) {
			throw new EmptyStackException();
		}
	}

	@Override
	public boolean isEmpty() {
		return storage.size() == 0;
	}

	@Override
	public void clear() {
		storage = new SinglyLinkedList<>();
		
	}
}
