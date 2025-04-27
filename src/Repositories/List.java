package Repositories;

public class List {
	private Object[] elements;
	public List() {
		this.elements = new Object[0];
	}
	public Object get(int index) {
		return elements[index];
	}
	public void add(Object element) {
		Object[] modifiedElements = (Object[]) new Object[this.elements.length + 1];
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
		Object[] modifiedElements = (Object[]) new Object[this.elements.length - 1];
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
		this.elements = (Object[]) new Object[0];
	}
}
