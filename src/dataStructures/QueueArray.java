package dataStructures;

import adt.Queue;

/**
 * Queue implementation which internally uses array.
 * Not queue.length-restricted.
 * @author Martin Kotevski
 * @date 24.10.16
 * @param <E> - Type of the elements to be stored in the queue
 */
public class QueueArray<E> implements Queue<E> {
	private E[] arr;
	private int first;
	private int next;
	private int numberOfElem;
	
	/**
	 * Queue constructor which creates new Queue with an initial
	 * queue.length of the parameter provided as an argument.
	 * @param initqueue.length - initial queue.length of the newly created Queue
	 */
	@SuppressWarnings("unchecked")  
	public QueueArray(int initCapacity) {
		arr = (E[]) new Object[initCapacity];
		numberOfElem = 0;
		next = 0;
		first = 0;
	}
	
	/**
	 * Empty Queue constructor which creates new Queue 
	 * with an initial queue.length of 10 elements.
	 */
	@SuppressWarnings("unchecked")  
	public QueueArray() {
		arr = (E[]) new Object[10];
		numberOfElem = 0;
		first = 0;
		next = 0;
	}
	
	/**
	 * Inserts the specified element into this queue if it is possible 
	 * to do so immediately without violating queue.length restrictions. 
	 * @param e - the element to add
	 * @return true if the element was added to this queue
	 */
	@Override
	public boolean enqueue(E e) {
		if (e == null)
			throw new NullPointerException();
		if (arr.length == numberOfElem) 
			resize((int) (arr.length * 1.5));
        arr[next++] = e;
        if (next == arr.length) 
        	next = 0;
        numberOfElem++;
        return true;
	}

	/**
	 * Retrieves and removes the head of this queue, 
	 * or returns null if this queue is empty.
	 * @return the head of this queue, or null if this queue is empty
	 */
	@Override
	public E dequeue() {
		if (numberOfElem == 0) 
			return null;
        E res = arr[first];
        arr[first] = null;
        if (++first == arr.length) 
        	first = 0;
        if (--numberOfElem > 0 && numberOfElem == arr.length / 4)
        	resize(arr.length / 2);
        return res;
	}
	
	/**
	 * Retrieves, but does not remove, the head of this queue, 
	 * or returns null if this queue is empty.
	 * @return the head of this queue, or null if this queue is empty
	 */
	@Override
	public E peek() {
		if (isEmpty())
			return null;
		return arr[next];
	}

	/**
	 * Checks if the queue is empty.
	 * @return true if the queue is empty and false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return numberOfElem == 0;
	}
	
	@SuppressWarnings("unchecked") 
	@Override
	public void clear() {
		arr = (E[]) new Object[10];
		numberOfElem = 0;
		next = 0;
		first = 0;		
	}
	
	/**
	 * @return The number of elements in the queue
	 */
	@Override
	public int count() {
		return numberOfElem;
	}
		
	/**
	 * Locally used method - resizes the capacity of the queue
	 * by the parameter provided as an argument.
	 * @param - the new capacity for the queue
	 */
	@SuppressWarnings("unchecked") 
	private void resize(int newCapacity) {
		E[] tmp = (E[]) new Object[newCapacity];

        for (int i = 0; i < numberOfElem; i++)
            tmp[i] = arr[(first + i) % arr.length];

        arr = tmp;
        first = 0;
        next = numberOfElem;
	}	
}