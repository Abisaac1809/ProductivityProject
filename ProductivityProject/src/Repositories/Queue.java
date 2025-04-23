package Repositories;

public class Queue<T> {

	private Node<T> firstNode;
	private Node<T> lastNode;
	private int size = 0;

	public void addNode(T data) {
		if (data == null) {
			throw new IllegalArgumentException("- QueueError: el elemento a agregar no puede ser nulo");
		}
		Node<T> nodo = new Node<T>(data);
		if (this.isEmpty()) {
			firstNode = nodo;
		} else {
			lastNode.setNext(nodo);
		}
		lastNode = nodo;
		size++;
	}

	public T cutNode() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("- QueueError: la cola esta vacia");
		}
		T data = firstNode.getData();
		firstNode = firstNode.getNext();

		if (firstNode == null) {
			lastNode = null;
		}

		size--;
		return data;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public T getFront() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("- QueueError: la cola esta vacia");
		}
		return this.firstNode.getData();
	}
}
