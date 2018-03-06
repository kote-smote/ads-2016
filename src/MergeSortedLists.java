/**
 * Algoritmi i Podatocni Strukturi - Lab  01 - Zadaca 2
 * 
 * Dadeni se dve ednostrano povrzani listi cii jazli sodrzat po eden  
 * priroden broj. Listite se sortirani vo rastecki redosled. Treba da se 
 * spojat dvete listi vo edna taka sto rezultantnata lista da e sortirana.
 * Sortiranjeto e podreduvanje so slevanje. Jazlite koi se javuvaat kako 
 * duplikati (od ista lista ili od razlicna) da se otstranat.
 * 
 * Zabeleska: 
 * Da se kreira podatocna struktura ednostrano povrzana lista i istata 
 * da se iskoristi vo zadacata..
 */

import dataStructures.SinglyLinkedList;

public class MergeSortedLists {
	
	public static SinglyLinkedList<Integer> 
		join(SinglyLinkedList<Integer> ls1, SinglyLinkedList<Integer> ls2) {
			
			SinglyLinkedList<Integer> merged = new SinglyLinkedList<Integer>();
			int i = 0, j = 0; // position of the current list's element
			int e1 = ls1.get(i); 
			int e2 = ls2.get(j); 
			
			// Puts only the first element in merged
			if (e1 < e2) {
				merged.append(e1);
				i++;
			} else {
				merged.append(e2);
				j++;
			}
			
			// stop if END of any list is reached (or both)
			while (i < ls1.size() && j < ls2.size()) {
				e1 = ls1.get(i); // Unboxing
				e2 = ls2.get(j); 
								
				if (e1 < e2) {
					if (e1 != merged.getLast()) 
						merged.append(e1);
					i++;
				} else if (e1 > e2) {
					if (e2 != merged.getLast()) 
						merged.append(e2);
					j++;
				} else { 
					if (e1 != merged.getLast()) 
						merged.append(e1);
					i++; j++;
				}
			}
			
			// list1 is completed, append the reminding of list2
			if (i == ls1.size()) {
				while (j < ls2.size()) {
					e2 = ls2.get(j);
					if (e2 != merged.getLast()) 
						merged.append(e2 = ls2.get(j));
					j++;
				}
			}
					
			// list2 is completed, append the reminding of lis1
			if (j == ls2.size()) {
				while (i < ls1.size()) {
					e1 = ls1.get(i);
					if (e1 != merged.getLast()) 
						merged.append(e1 = ls1.get(i));
					i++;
				}
			}
			
			return merged;
	}
	
	public static void main(String[] args) {
		// TODO uncomment after updating sll list
//		SinglyLinkedList<Integer> ls1, ls2, merged;
//
//		System.out.println("=== TEST 1 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{2, 3, 5});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{3});
//		System.out.println("list1 : " + ls1);
//		System.out.println("list2 : " + ls2);
//		merged = MergeSortedLists.join(ls1, ls2);
//		System.out.println("merged: " + merged);
//		System.out.println();
//
//		System.out.println("=== TEST 2 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{1, 4, 5, 7, 7, 7, 8});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{4, 4});
//		System.out.println("list1 : " + ls1);
//		System.out.println("list2 : " + ls2);
//		merged = MergeSortedLists.join(ls1, ls2);
//		System.out.println("merged: " + merged);
//		System.out.println();
//
//		System.out.println("=== TEST 3 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{3, 3, 3});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{3, 3});
//		System.out.println("list1 : " + ls1);
//		System.out.println("list2 : " + ls2);
//		merged = MergeSortedLists.join(ls1, ls2);
//		System.out.println("merged: " + merged);
//		System.out.println();
//
//		System.out.println("=== TEST 4 ===");
//		ls1 = new SinglyLinkedList<Integer>();ls1.append(1);
//		ls2 = new SinglyLinkedList<Integer>();ls2.append(1);
//		System.out.println("list1 : " + ls1);
//		System.out.println("list2 : " + ls2);
//		merged = MergeSortedLists.join(ls1, ls2);
//		System.out.println("merged: " + merged);
//		System.out.println();
//
//		System.out.println("=== TEST 5 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{1, 2, 3, 4, 5});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{1, 2, 3, 4, 5});
//		System.out.println("list1 : " + ls1);
//		System.out.println("list2 : " + ls2);
//		merged = MergeSortedLists.join(ls1, ls2);
//		System.out.println("merged: " + merged);
//		System.out.println();
//
//		System.out.println("=== TEST 6 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{1, 4, 5, 6, 7});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{4, 4, 5, 6, 7});
//		System.out.println("list1 : " + ls1);
//		System.out.println("list2 : " + ls2);
//		merged = MergeSortedLists.join(ls1, ls2);
//		System.out.println("merged: " + merged);
//		System.out.println();
//
//		System.out.println("=== TEST 7 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{4, 4});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{4, 4, 5});
//		System.out.println("list1 : " + ls1);
//		System.out.println("list2 : " + ls2);
//		merged = MergeSortedLists.join(ls1, ls2);
//		System.out.println("merged: " + merged);
//		System.out.println();
//
//		System.out.println("=== TEST 8 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{2, 2, 3, 3, 3, 3});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{1, 1, 1});
//		System.out.println("list1 : " + ls1);
//		System.out.println("list2 : " + ls2);
//		merged = MergeSortedLists.join(ls1, ls2);
//		System.out.println("merged: " + merged);
//		System.out.println();
//
//		System.out.println("=== TEST 9 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{2, 4, 6, 8});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{1, 3, 5, 7, 9});
//		System.out.println("list1 : " + ls1);
//		System.out.println("list2 : " + ls2);
//		merged = MergeSortedLists.join(ls1, ls2);
//		System.out.println("merged: " + merged);
//		System.out.println();
//
//		System.out.println("=== TEST 10 ===");
//		ls1 = new SinglyLinkedList<Integer>(new Integer[]{1, 1, 1});
//		ls2 = new SinglyLinkedList<Integer>(new Integer[]{2, 2});
//		System.out.println("list1 : " + ls1);
//		System.out.println("list2 : " + ls2);
//		merged = MergeSortedLists.join(ls1, ls2);
//		System.out.println("merged: " + merged);
//		System.out.println();
	}

}
