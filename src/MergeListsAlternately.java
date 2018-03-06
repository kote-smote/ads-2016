/**
 * Algoritmi i Podatocni Strukturi - Lab  01 - Zadaca 3
 * 
 * Dadeni se dve ednostrano povrzani listi cii sto jazli sodrzat po eden
 * priroden broj. Treba da se spojat dvete listi vo edna rezultantna
 * na toj nacin sto naizmenicno prvo ke se dodavaat prvite dva jazli od 
 * prvata lista vo rezultantnata, pa prvite dva od vtorata lista, 
 * pa slednite dva od prvata, pa slednite dva od vtorata itn. 
 *  
 * Zabeleska: 
 * Da se kreira podatocna struktura ednostrano povrzana lista i istata 
 * da se iskoristi vo zadacata..
 */

import dataStructures.SinglyLinkedList;

public class MergeListsAlternately {
	// TODO update this file after updating sll class
//	public static <E> SinglyLinkedList<E> join(SinglyLinkedList<E> ls1, SinglyLinkedList<E> ls2) {
//		SinglyLinkedList<E> merged = new SinglyLinkedList<E>();
//		E e;
//		int i = 0;
//
//		while (i < ls1.size() && i < ls2.size()) {
//				e = ls1.get(i);
//				merged.append(e);
//				if (i+1 < ls1.size()) {
//					e = ls1.get(i+1);
//					merged.append(e);
//				}
//				e = ls2.get(i);
//				merged.append(e);
//				if (i+1 < ls2.size()) {
//					e = ls2.get(i+1);
//					merged.append(e);
//				}
//			i += 2;
//		}
//
//		if (ls1.size() < ls2.size()) {
//			while (i < ls2.size()) {
//				e = ls2.get(i++);
//				merged.append(e);
//			}
//		}
//
//		if (ls1.size() > ls2.size()) {
//			while (i < ls1.size()) {
//				e = ls1.get(i++);
//				merged.append(e);
//			}
//		}
//
//		return merged;
//	}
//
//	public static void main(String[] args) {
//		SinglyLinkedList<Integer> ls1;
//		SinglyLinkedList<Integer> ls2;
//		SinglyLinkedList<Integer> merged;
//
//		System.out.println("=== TEST 1 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{1, 2, 3, 4});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{5, 6, 7});
//		System.out.println("list1: " + ls1);
//		System.out.println("list2: " + ls2);
//		merged = MergeListsAlternately.join(ls1, ls2);
//		System.out.println(merged);
//		System.out.println();
//
//		System.out.println("=== TEST 2 ===");
//		ls1 = new SinglyLinkedList<Integer>();ls1.append(1);
//		ls2 = new SinglyLinkedList<Integer>();ls2.append(2);
//		System.out.println("list1: " + ls1);
//		System.out.println("list2: " + ls2);
//		merged = MergeListsAlternately.join(ls1, ls2);
//		System.out.println(merged);
//		System.out.println();
//
//		System.out.println("=== TEST 3 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{1, 2});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{3, 4, 5, 6, 7});
//		System.out.println("list1: " + ls1);
//		System.out.println("list2: " + ls2);
//		merged = MergeListsAlternately.join(ls1, ls2);
//		System.out.println(merged);
//		System.out.println();
//
//		System.out.println("=== TEST 4 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{5, 7, 9});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{1, 1, 4, 5, 6, 8, 9, 4});
//		System.out.println("list1: " + ls1);
//		System.out.println("list2: " + ls2);
//		merged = MergeListsAlternately.join(ls1, ls2);
//		System.out.println(merged);
//		System.out.println();
//
//		System.out.println("=== TEST 5 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{4, 2, 87, 94, 2});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{66, 8});
//		System.out.println("list1: " + ls1);
//		System.out.println("list2: " + ls2);
//		merged = MergeListsAlternately.join(ls1, ls2);
//		System.out.println(merged);
//		System.out.println();
//
//		System.out.println("=== TEST 6 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{110, 111, 98, 102, 115, 118});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{119, 113, 120, 112});
//		System.out.println("list1: " + ls1);
//		System.out.println("list2: " + ls2);
//		merged = MergeListsAlternately.join(ls1, ls2);
//		System.out.println(merged);
//		System.out.println();
//	}
}
