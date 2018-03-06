import java.util.Random;
import adt.Queue;
import dataStructures.QueueArray;
import dataStructures.QueueList;

public class TestQueue2 {

	public static void main(String[] args) {
		
		Random rand = new Random();
		
		Queue<Integer> qArr = new QueueArray<>();
		Queue<Integer> qLis = new QueueList<>();
		
		for (int i = 0; i < 300; i++) {
			qArr.enqueue(i);
			qLis.enqueue(i);
		}
		
		for (int i = 0; i < 100; i++) {
			if (rand.nextInt(2) == 0) {
				qArr.dequeue();
				qLis.dequeue();
			} else {
				int num = rand.nextInt(50);
				qArr.enqueue(num);
				qLis.enqueue(num);
			}
		}
		
		System.out.println("Number of elements:");
		System.out.println("QueueArray: " + qArr.count());
		System.out.println("QueueList: " + qLis.count());
		Integer n;
		while ((n = qArr.dequeue()) != null)
			System.out.print(n + " ");
		System.out.println();
		
		while ((n = qLis.dequeue()) != null)
			System.out.print(n + " ");
		System.out.println();	
		
	}
}
