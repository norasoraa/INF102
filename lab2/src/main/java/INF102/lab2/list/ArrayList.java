package INF102.lab2.list;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

	
	public static final int DEFAULT_CAPACITY = 10;
	
	private int n;
	
	private Object elements[];
	
	public ArrayList() {
		elements = new Object[DEFAULT_CAPACITY];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return (T) this.elements[index];
	}
	
	@Override
	public void add(int index, T element) {
		if (this.elements.length == size()) {
			Object newList[] = new Object[this.elements.length * 2];
			System.arraycopy(this.elements, 0, newList, 0, size());
			this.elements = newList;
		}
		for (int i = this.elements.length-1; i > index; i--) {
			this.elements[i] = this.elements[i-1];
		}
		elements[index] = element;
		this.n++;
	}

	//fra oppgavegjennomgang 
	public void fasitAdd(int index, T element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (size() + 1 > elements.length) {
			ensureCapacity();
		}
		while (index < size()) {
			T temp = get(index);
			elements[index] = element;
			element = temp;
			index++;
		}
		elements[index] = element;
		n++;
	}

	//fra oppgavegjennomgang 
	private void ensureCapacity() {
		int newSize = 2 * elements.length;
		elements = Arrays.copyOf(elements, newSize);
	}
	
	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n == 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder(n*3 + 2);
		str.append("[");
		for (int i = 0; i < n; i++) {
			str.append((T) elements[i]);
			if (i != n-1)
				str.append(", ");
		}
		str.append("]");
		return str.toString();
	}
}