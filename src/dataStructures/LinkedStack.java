package dataStructures;

import java.util.EmptyStackException;

import adt.Stack;
import unit.SLNode;

public class LinkedStack<E> implements Stack<E> {
	SLNode<E>top;
	
	public LinkedStack() {
		top = null;
	}

	@Override
	public E push(E e) {
		if (e == null)
			throw new NullPointerException();
		SLNode<E> newNode = new SLNode<E>(e, top);
		top = newNode;
		return newNode.data;
	}

	@Override
	public E pop() {
		if (isEmpty())
			throw new EmptyStackException();
		E toBeDeleted = top.data;
		top = top.link;
		return toBeDeleted;
	}

	@Override
	public E peek() {
		if (isEmpty())
			return null;
		return top.data;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public void clear() {
		top = null;
	}
}
