package Structures;

public class Node<T> {
	private T data;
	private Node<T> nextNode;

	public Node(T element) {
		if (element == null) {
			throw new IllegalArgumentException("- NodeError: el elemento a agregar no puede ser nulo");
		}
		data = element;
		nextNode = null;
	}

	// only use this constructor if you want to copy a node
	// public Node(Node<T> node) {
	// if (node == null) {
	// throw new IllegalArgumentException("- NodeError: el elemento a agregar no
	// puede ser nulo");
	// }
	// data = node.getData();
	// nextNode = node.getNext();
	// }

	public void setData(T element) {
		if (element == null) {
			throw new IllegalArgumentException("- NodeError: el elemento a agregar no puede ser nulo");
		} else if (element.equals(data)) {
			throw new IllegalArgumentException(
					"- NodeError: el elemento a agregar no puede ser igual al elemento actual");
		}
		data = element;
	}

	public void setNext(Node<T> element) {
		nextNode = element;
	}

	public T getData() {
		return data;
	}

	public Node<T> getNext() {
		return nextNode;
	}

}
