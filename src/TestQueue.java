import adt.Queue;
import dataStructures.LinkedQueue;
import dataStructures.QueueArray;
import dataStructures.QueueList;

public class TestQueue {
	
	public static void main(String[] args) {
		
		/* TESTING QueueArray - Queue implemented with an array */
		System.out.println("TESTING QueueArray");
		Queue<Integer> q = new QueueArray<>(8);
		
		for (int i = 0; i < 7; i++)
			q.enqueue(i);
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		
		for (int i = 0; i < 9; i++)
			q.enqueue(i);
		
		q.clear();
		
		for (int i = 1; i <= 40; i++) 
			q.enqueue(i);
		for (int i = 1; i <= 31; i++) 
			q.dequeue();
		
		q.dequeue();
		q.enqueue(9);
		q.dequeue();
		q.dequeue();
		q.enqueue(13);
		System.out.println("number of elements: " + q.count());
	
		Integer num;
		while ((num = q.dequeue()) != null)
			System.out.print(num + " ");
		System.out.println();
		
		q.enqueue(3);
		q.enqueue(4);
		q.clear();
		System.out.println("number of elements: " + q.count());
		
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.dequeue();
		q.dequeue();
		while (!q.isEmpty())
			System.out.print(q.dequeue() + " ");
		System.out.println();
		System.out.println(q.count());
		
		q.enqueue(5);
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.enqueue(9); 
		while (!q.isEmpty())
			System.out.print(q.dequeue() + " ");
		System.out.println();
		
		/* TESTING QueueList - Queue implemented with a list */
		System.out.println();
		System.out.println("TESTING QueueList");
		q = new QueueList<Integer>();
		// Constructor with parameters is not allowed for listq.Queue
//		queue = new QueueList<Integer>(5); 
		
		for (int i = 0; i < 7; i++)
			q.enqueue(i);
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		
		for (int i = 0; i < 9; i++)
			q.enqueue(i);
		
		q.clear();
		
		for (int i = 1; i <= 40; i++) 
			q.enqueue(i);
		for (int i = 1; i <= 31; i++) 
			q.dequeue();
		
		q.dequeue();
		q.enqueue(9);
		q.dequeue();
		q.dequeue();
		q.enqueue(13);
		System.out.println("number of elements: " + q.count());
	
		Integer n;
		while ((n = q.dequeue()) != null)
			System.out.print(n + " ");
		System.out.println();
		
		q.enqueue(3);
		q.enqueue(4);
		q.clear();
		System.out.println("number of elements: " + q.count());
		
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.dequeue();
		q.dequeue();
		while (!q.isEmpty())
			System.out.print(q.dequeue() + " ");
		System.out.println();
		System.out.println(q.count());
		
		q.enqueue(5);
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.enqueue(9); 
		while (!q.isEmpty())
			System.out.print(q.dequeue() + " ");
		System.out.println();
		
		/* TESTING Linked - Queue implementation based on list implementation */
		System.out.println();
		System.out.println("TESTING LinkedQueue");
		q = new LinkedQueue<Integer>();
		// Constructor with parameters is not allowed for listq.Queue
//		queue = new QueueList<Integer>(5); 
		
		for (int i = 0; i < 7; i++)
			q.enqueue(i);
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		
		for (int i = 0; i < 9; i++)
			q.enqueue(i);
		
		q.clear();
		
		for (int i = 1; i <= 40; i++) 
			q.enqueue(i);
		for (int i = 1; i <= 31; i++) 
			q.dequeue();
		
		q.dequeue();
		q.enqueue(9);
		q.dequeue();
		q.dequeue();
		q.enqueue(13);
		System.out.println("number of elements: " + q.count());
	
		while ((n = q.dequeue()) != null)
			System.out.print(n + " ");
		System.out.println();
		
		q.enqueue(3);
		q.enqueue(4);
		q.clear();
		System.out.println("number of elements: " + q.count());
		
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.dequeue();
		q.dequeue();
		while (!q.isEmpty())
			System.out.print(q.dequeue() + " ");
		System.out.println();
		System.out.println(q.count());
		
		q.enqueue(5);
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.enqueue(9); 
		while (!q.isEmpty())
			System.out.print(q.dequeue() + " ");
		System.out.println();
		
	}
}