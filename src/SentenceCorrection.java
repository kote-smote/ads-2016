import java.util.Scanner;

/**
 * APS - Prv parcijalen ispit - 2016
 * Na vlez e dadena recenica. Smesti ja recenicata vo ednostrano povrzana
 * lista kade sekoj jazel ke cuva po eden karakter od recenicata. 
 * Pritoa recenicata e pogresno napisana kade prvata bukva od sekoj zbor
 * e stavena posledna. Site recenici zavrsuvaat so '.'.
 * Koregiraj ja recenicata i ispecati ja na ekran.
 * Napomena: Na raspolaganje imate samo edna ednostrano povrzana lista,
 * zabraneto e koristenje na pomosni strukturi kako nizi i slicno.
 * 
 * Vlez: remetoV e oncevos.
 * Izlez: Vremeto e soncevo.
 * 
 * Vlez: eK ademej ananib.
 * Izlez: Ke jademe banani.
 * 
 * Vlez: I oat e eston.
 * Izlez: I toa e nesto.
 */
public class SentenceCorrection {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CharList sentence = new CharList();
		
		char[] chars = sc.nextLine().toCharArray();
		for (int i = 0; i < chars.length; i++)
			sentence.append(chars[i]);
		
		sentence.correct();
		System.out.println(sentence);
		sc.close();
	}

}

class CharList {
	private CharNode head;
	private CharNode tail;
	
	public CharList() {
		head = null;
		tail = null;
	}
	
	public void append(char ch) {
		CharNode newNode = new CharNode(ch, null);
		if (head == null) {
			head = tail = newNode;
			return;
		}
		tail.next = newNode;
		tail = newNode;
	}
		
	public void correct() {
		CharNode first = new CharNode(' ', head);
		CharNode tmp = head;
		CharNode onSpace = head.next.next;
		
		// Cover the case when the first word has only one letter
		if (first.next == head && tmp.next.character == ' ') 
			first = tmp.next;
				
		while (onSpace != null) {	
			if (onSpace.character == ' ' || onSpace.character == '.') {
				// if a word has only one letter
				if(first == tmp) {
					first = onSpace;
					tmp = tmp.next;
					onSpace = onSpace.next;
					continue;
				}
				
				CharNode newNode = new CharNode(tmp.next.character, first.next);
				if (first.next == head) // case for the first word
					head = newNode;
				else 
					first.next = newNode;
				
				// place first in front of every word
				first = onSpace;
				tmp.next = tmp.next.next; // delete node with misplaced letter
			}
			// difference between onSpace and tmp should be 2,
			// so advance tmp only if letter wasn't deleted.
			if (tmp.next.next == onSpace) 
				tmp = tmp.next; 
			onSpace = onSpace.next;
		}
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		CharNode tmp = head;
		while (tmp != null) {
			result.append(tmp.character);
			tmp = tmp.next;
		}
		return result.toString();
	}
	
	private class CharNode {
		char character;
		CharNode next;
		
		public CharNode(char ch, CharNode next) {
			this.character = ch;
			this.next = next;
		}
		
		public String toString() {
			return String.valueOf(character);
		}
	}
}
