import java.util.Scanner;

/**
 * Dadena e dvojno povrzana lista od fvojno povrzani listi. 
 * Da se najde suma na sekoja od podlistite, a potoa proizvod od ovie sumi.
 * 
 * Vlez: broj N koj kazuva kolku listi ima i broj M koj kazuva kolku elementi
 * ima vo sekoja lista. Vo slednite M linii se podatocite 
 * 1<=A<=1000 za sekoja od listite.
 * 
 * Izlez: Eden broj wto e proizvodot na sumite od nizite. So sedum decimali(long)
 * 
 * Primer vlez:
 * 4
 * 4
 * 6 13 16 7 
 * 7 23 9 11 
 * 8 0 8 19 
 * 6 6 9 11
 * 
 * Izlez: 2352000
 */
public class ListOfLists {

    public static long findMagicNumber(DLL<DLL<Integer>> list) {
    	int length = list.length();
		long proizvod = 1;
    	long suma = 0;
		for (int i = 0; i < length; i++) {
			DLL<Integer> subList = list.getFirst().element;
			DLLNode<Integer> node = subList.getFirst();
			while (node != null) {
				suma += node.element;
				node = node.succ;
			}
			proizvod *= suma;
            suma = 0;
            list.deleteFirst();
		}
    	return proizvod;
	}
    
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		DLL<DLL<Integer>> list = new DLL<DLL<Integer>>();
		for (int i = 0; i < n; i++) {
			DLL<Integer> tmp = new DLL<Integer>();
			for (int j = 0; j < m; j++) {
				tmp.insertLast(in.nextInt());
			}
			list.insertLast(tmp);
		}
		in.close();
		System.out.println(findMagicNumber(list));
	}

}

class DLLNode<E> {
	protected E element;
	protected DLLNode<E> pred, succ;

	public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
		this.element = elem;
		this.pred = pred;
		this.succ = succ;
	}

	@Override
	public String toString() {
		return "<-" + element.toString() + "->";
	}
}

class DLL<E> {
	private DLLNode<E> first, last;

	public DLL() {
		// Construct an empty SLL
		this.first = null;
		this.last = null;
	}

	public void deleteList() {
		first = null;
		last = null;
	}

	public int length() {
		int ret;
		if (first != null) {
			DLLNode<E> tmp = first;
			ret = 1;
			while (tmp.succ != null) {
				tmp = tmp.succ;
				ret++;
			}
			return ret;
		} else
			return 0;

	}

	public void insertFirst(E o) {
		DLLNode<E> ins = new DLLNode<E>(o, null, first);
		if (first == null)
			last = ins;
		else
			first.pred = ins;
		first = ins;
	}

	public void insertLast(E o) {
		if (first == null)
			insertFirst(o);
		else {
			DLLNode<E> ins = new DLLNode<E>(o, last, null);
			last.succ = ins;
			last = ins;
		}
	}

	public void insertAfter(E o, DLLNode<E> after) {
		if (after == last) {
			insertLast(o);
			return;
		}
		DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
		after.succ.pred = ins;
		after.succ = ins;
	}

	public void insertBefore(E o, DLLNode<E> before) {
		if (before == first) {
			insertFirst(o);
			return;
		}
		DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
		before.pred.succ = ins;
		before.pred = ins;
	}

	public E deleteFirst() {
		if (first != null) {
			DLLNode<E> tmp = first;
			first = first.succ;
			if (first != null) first.pred = null;
			if (first == null)
				last = null;
			return tmp.element;
		} else
			return null;
	}

	public E deleteLast() {
		if (first != null) {
			if (first.succ == null)
				return deleteFirst();
			else {
				DLLNode<E> tmp = last;
				last = last.pred;
				last.succ = null;
				return tmp.element;
			}
		}
		// else throw Exception
		return null;
	}

	@Override
	public String toString() {
		String ret = new String();
		if (first != null) {
			DLLNode<E> tmp = first;
			ret += tmp + "<->";
			while (tmp.succ != null) {
				tmp = tmp.succ;
				ret += tmp + "<->";
			}
		} else
			ret = "Prazna lista!!!";
		return ret;
	}

	public DLLNode<E> getFirst() {
		return first;
	}

	public DLLNode<E> getLast() {

		return last;
	}

}