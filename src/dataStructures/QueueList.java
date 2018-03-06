package dataStructures;

import adt.Queue;

public class QueueList<E> implements Queue<E> {
	private SinglyLinkedList<E> data;
	
	public QueueList() {
		data = new SinglyLinkedList<>();
	}
	
	@Override
	public boolean enqueue(E e) {
		data.append(e);
		return false;
	}

	@Override
	public E peek() {
		return data.getFirst();
	}

	@Override
	public E dequeue() {
		E res = this.data.getLast();
		this.data.deleteFirst();
		return res;
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	@Override
	public void clear() {
		data = new SinglyLinkedList<>();
	}
	
	@Override
	public int count() {
		return data.size();
	}
	
}
