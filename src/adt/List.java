package adt;

public interface List<E> extends Iterable<E> {
	
	int size();
	
	boolean isEmpty();
	
	void addHead(E e);
	
	void append(E e);
	
	void add(int pos, E e);
	
	boolean deleteFirst();
	
	boolean deleteLast();
	
	void deleteAtIndex(int index);
	
	void deleteElement(E e);
		
	E getFirst();
	
	E getLast();
	
	E get(int pos);
	
	String toString();
	
}
