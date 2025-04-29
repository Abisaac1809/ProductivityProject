package Structures;

public class Stack<T> {
	private Node<T> topNode;
	private int size;

	public Stack() {
		topNode = null;
		size = 0;
	}

	public void top(T data) {
		Node<T> nodo = new Node<T>(data);
		nodo.setNext(this.topNode);
		this.topNode = nodo;
		size++;
	}

	public T pop() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("- StackError: la pila esta vacia");
		}
		T data = topNode.getData();
		topNode = topNode.getNext();
		size--;
		return data;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public T getTop() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("- StackError: la pila esta vacia");
		}
		return this.topNode.getData();
	}

}
