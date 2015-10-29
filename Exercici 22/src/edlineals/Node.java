package edlineals;

public class Node<E> {
	public E inf;
	public Node<E> seg;

	public Node(E data){
		this.inf = data;
	}
}