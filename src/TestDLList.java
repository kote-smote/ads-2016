import dataStructures.DoublyLinkedList;

public class TestDLList {

	public static void main(String[] args) {
		DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
		
		dll.addHead(4);
		dll.deleteFirst();
		dll.append(3);
		dll.addHead(5);
		dll.append(7);
		dll.deleteFirst();
		dll.append(8);
		dll.deleteLast();
		dll.append(10);
		dll.deleteLast();
		dll.append(12);
		dll.append(9);
		dll.addHead(2);
		
		
		System.out.println("first: " + dll.getFirst());
		System.out.println("last: " + dll.getLast());
//		System.out.println(dll.get(1));
		System.out.println(dll);
		dll.deleteAtIndex(0);
		System.out.println(dll);
		dll.deleteElement(7);
		System.out.println(dll);
		dll.add(2, 4);
		System.out.println(dll);
		System.out.println(dll);
		System.out.println(dll);
		System.out.println(dll);
		System.out.println(dll);
		System.out.println(dll);
		
		System.out.println("index of 12: " + dll.indexOf(12));
		System.out.println("index of 98: " + dll.indexOf(98));
		
		DoublyLinkedList<Integer> ls1 = new DoublyLinkedList<>(
				new Integer[]{2, 3, 4, 6, 1});
		DoublyLinkedList<Integer> ls2 = new DoublyLinkedList<>(ls1);
		System.out.println(ls2);
	}
}
