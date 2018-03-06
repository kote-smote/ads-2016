package dataStructures;

import adt.Queue;
import unit.SLNode;

public class LinkedQueue<E> implements Queue<E> {
	SLNode<E>head;
	SLNode<E>tail;
	int count;
	
	public LinkedQueue() {
		head = tail = null;
		count = 0;
	}
	
	@Override
	public boolean enqueue(E e) {
		if (e == null)
			throw new NullPointerException();
		if (isEmpty()) {
			SLNode<E> queued = new SLNode<>(e, null);
			head = tail = queued;
			count++;
			return true;
		}
		SLNode<E> newNode = new SLNode<>(e, null);
		tail.link = newNode;
		tail = newNode;
		count++;
		return false;
	}

	@Override
	public E dequeue() {
		if (isEmpty())
			throw new EmptyQueueException();
		E dequeued = head.data;
		head = head.link;
		count--;
		return dequeued;
	}

	@Override
	public E peek() {
		if (isEmpty())
			throw new EmptyQueueException();
		return head.data;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public void clear() {
		head = tail = null;
		count = 0;
		
	}

	@Override
	public int count() {
		return count;
	}
}

class EmptyQueueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyQueueException() { super(); }
}


