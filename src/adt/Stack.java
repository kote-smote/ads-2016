package adt;

public interface Stack<E> {
	
	public E push(E e);
	
	public E pop();
	
	public E peek();
	
	public boolean isEmpty();
	
	public void clear();
}
