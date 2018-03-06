package unit;

public class SLNode<E> {
	public E data;
	public SLNode<E> link;

	public SLNode(E data, SLNode<E> link) {
		this.data = data;
		this.link = link;
	}
}
