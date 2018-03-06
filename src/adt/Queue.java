package adt;

public interface Queue<E> {
	
	public boolean enqueue(E e);
	
	public E dequeue();
	
	public E peek();
	
	public boolean isEmpty();
		
	public void clear();
	
	public int count();
}
