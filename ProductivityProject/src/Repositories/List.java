package Repositories;
@SuppressWarnings("unchecked")

public class List<T> {
	private T[] elements = (T[]) new Object[0];
	public T get(int index) {
		return elements[index];
	}
	public void add(T element) {
		T[] modifiedElements = (T[]) new Object[this.elements.length + 1];
		for (int i = 0; i < elements.length; i++) {
			modifiedElements[i] = this.elements[i];
		}
		modifiedElements[this.elements.length] = element;
		this.elements = modifiedElements;

		modifiedElements = null;
	}
	public int size() {
		return this.elements.length;
	}
	public void remove(int index) {
		T[] modifiedElements = (T[]) new Object[this.elements.length - 1];
		this.elements[index] = null;
                int k=0;
		for (int i = 0; i < elements.length; i++) {
			if (this.elements[i] != null) {
				modifiedElements[k] = this.elements[i];
                                k++;
			}
		}
		this.elements = modifiedElements;
		modifiedElements = null;
	}
	public void removeAll() {
		this.elements = (T[]) new Object[0];
	}
}
