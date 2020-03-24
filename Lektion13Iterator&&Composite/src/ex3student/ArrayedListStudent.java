package ex3student;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayedListStudent<T> implements Iterable<T>
{
	// array to store the entries in;
	// entries have indices in [0, size-1]
	private T[] items;
	// number of entries in the list;
	// index of the first empty slot in items
	private int size;

	/**
	 * Creates an ArrayList with capacity 16.
	 */
	public ArrayedListStudent() {
		this(16);
	}

	/**
	 * Creates an ArrayList. Requires: capacity >= 1.
	 */
	public ArrayedListStudent(int capacity) {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[capacity];
		this.items = temp;
		this.size = 0;
	}

	// Increases the capacity of the deque.
	private void increaseCapacity() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[2 * this.items.length];
		for (int i = 0; i < this.size; i++) {
			temp[i] = this.items[i];
		}
		this.items = temp;
	}

	/**
	 * Adds the entry at the end of this list.
	 */
	public void add(T entry) {
		if (this.size == this.items.length) {
			this.increaseCapacity();
		}

		this.items[this.size] = entry;
		this.size++;
	}

	/**
	 * Adds the entry at the index. Throws IndexOutOfBoundsException if index is not
	 * in [0, size()].
	 */
	public void add(int index, T entry) {
		if (index < 0 || index > this.size)
			throw new IndexOutOfBoundsException();

		if (index == this.size) {
			this.add(entry);
			return;
		}

		if (this.size == this.items.length) {
			this.increaseCapacity();
		}

		for (int i = this.size; i > index; i--) {
			this.items[i] = this.items[i - 1];
		}
		this.items[index] = entry;
		this.size++;
	}

	/**
	 * Removes and returns the entry at the index. Throws IndexOutOfBoundsException
	 * if this list is empty or index is not in [0, size()-1].
	 */
	public T remove(int index) {
		if (index < 0 || index > this.size - 1 /* || size == 0 */)
			throw new IndexOutOfBoundsException();

		T entry = this.items[index];
		for (int i = index; i < this.size - 1; i++) {
			this.items[i] = this.items[i + 1];
		}
		this.items[this.size - 1] = null;
		this.size--;
		return entry;
	}

	/**
	 * Replaces and returns the old entry at the index with the specified entry.
	 * Throws IndexOutOfBoundsException if this list is empty or index is not in [0,
	 * size()-1].
	 */
	public T replace(int index, T entry) {
		if (index < 0 || index > this.size - 1 /* || this.size == 0 */)
			throw new IndexOutOfBoundsException();

		T oldEntry = this.items[index];
		this.items[index] = entry;
		return oldEntry;
	}

	/**
	 * Returns the entry at the index. Throws IndexOutOfBoundsException if this list
	 * is empty or index is not in [0, size()-1].
	 */
	public T get(int index) {
		if (index < 0 || index > this.size - 1 /* || this.size == 0 */)
			throw new IndexOutOfBoundsException();

		T entry = this.items[index];
		return entry;
	}

	/**
	 * Return true if the entry is in this list.
	 */
	public boolean contains(T entry) {
		boolean found = false;
		int i = 0;
		while (!found && i < this.size) {
			if (this.items[i].equals(entry)) {
				found = true;
			}
			i++;
		}
		return found;
	}

	/**
	 * Returns the number of entries in this list.
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Returns true if this list is empty.
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Returns an array containing all entries in the same order as in this list.
	 */
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] copy = (T[]) new Object[this.size];
		for (int i = 0; i < this.size; i++) {
			copy[i] = this.items[i];
		}
		return copy;
	}

	@Override
	public String toString() {
		if (this.isEmpty())
			return ("[]");

		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < this.size; i++) {
			sb.append(", " + this.items[i]);
		}
		sb.append("]");
		sb.delete(1, 3);
		return sb.toString();
	}

	// -------------------------------------------------------------------------
	// Ex. 3
	private class ArrayedListStudentIterator implements Iterator<T> {
		private int count = 0;
		
		
		@Override
		public boolean hasNext() {
			if (count == items.length)
			return false; 
			else if (items[count] == null)
				return false;
			else
				return true;
		}

		@Override
		public T next() {
			if (count == items.length)
				throw new NoSuchElementException();
			count++;
			return items[count - 1];
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayedListStudentIterator();
	}

}
