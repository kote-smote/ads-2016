import java.util.Scanner;

class NodeEmployee implements Comparable<NodeEmployee> {
	protected int id;
    protected int salary;
    protected NodeEmployee next, prev;
    
    public NodeEmployee(int id, int salary) {
    	this.id = id;
        this.salary = salary;
        next = prev = null;
    }
    
    public NodeEmployee(int id, int salary, NodeEmployee prev, NodeEmployee next) {
    	this.id = id;
        this.salary = salary;
        this.prev = prev;
        this.next = next;
    }
    
    @Override 
    public int compareTo(NodeEmployee other) {
    	return this.id < other.id ? -1 : this.id > other.id ? 1 : 0;
    }
    
    @Override 
    public String toString() {
    	return id + " " + salary;
    }
}

public class DLLKompanija {
	private NodeEmployee head, tail;
	private int size;
	
	public DLLKompanija() {
		head = tail = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		return size;
	}
	
	public void addFirst(int id, int salary) {
		NodeEmployee newNode 
			= new NodeEmployee(id, salary, null, head);
		if (isEmpty())
			tail = newNode;
		else 
			head.prev = newNode;
		head = newNode;
		size++;
	}
	
	public void append(int id, int salary) {
		if (isEmpty()) {
			addFirst(id, salary);
			return;
		}
		NodeEmployee newNode 
			= new NodeEmployee(id, salary, tail, null);
		tail.next = newNode;
		tail = newNode;
		size++;		
	}
	
	public void deleteNode(NodeEmployee toBeDeleted) {
		if (isEmpty()) {
			size--;
			return;
		}
		if (size == 1) {
			head = tail = null;
			size--;
			return;
		}
		if (toBeDeleted == head) {
			head = head.next;
			head.prev = null;
			size--;
			return;
		}
		if (toBeDeleted == tail) {
			tail = tail.prev;
			tail.next = null;
			size--;
			return;
		}
		toBeDeleted.prev.next = toBeDeleted.next;
		toBeDeleted.next.prev = toBeDeleted.prev;
		size--;
	}
	
	public void removeLowerThan(int salary) { 
		NodeEmployee tmp = head;
		while (tmp != null) {
			if (tmp.salary < salary) 
				deleteNode(tmp);
			tmp = tmp.next;			
		}
	}
	
	public void sortDescending() {
		if (isEmpty()) {
			System.out.print("nema");
			return;
		}
		NodeEmployee tmp1 = head;
		NodeEmployee tmp2;
		
		while (tmp1.next != null) {
			tmp2 = tmp1.next;
			while (tmp2 != null) {
				if (tmp1.compareTo(tmp2) < 0) {
					int tmpId = tmp1.id;
					int tmpSal = tmp1.salary;
					tmp1.id = tmp2.id;
					tmp1.salary = tmp2.salary;
					tmp2.id = tmpId;
					tmp2.salary = tmpSal;
				}
				tmp2 = tmp2.next;
			}
			tmp1 = tmp1.next;
		}
	}
	
	public void print() {
		if (isEmpty()) {
			System.out.print("");
			return;
		}		
		NodeEmployee tmp = head;
		while (tmp != null) {
			System.out.println(tmp);
			tmp = tmp.next;
		}
	}
	
	public static void main(String[] args) {
		DLLKompanija company = new DLLKompanija();
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		
		for (int i = 0; i < n; i++) {
			int id = Integer.parseInt(sc.nextLine());
			int salary = Integer.parseInt(sc.nextLine());
			company.append(id, salary);
		}
		int iznos = Integer.parseInt(sc.nextLine());
		
		company.removeLowerThan(iznos);
		company.sortDescending();
		company.print();
		
		sc.close();
		
	}
}