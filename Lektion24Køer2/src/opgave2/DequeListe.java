package opgave2;

import java.util.NoSuchElementException;

import queues.Deque;

public class DequeListe implements Deque {

	private Node first;
	private Node last;

	@Override
	public String toString() {
		return "SortedLinkedList [first=" + first + "]";
	}

	class Node {

		public Object data;
		public Node next;
		public Node previous;

		@Override
		public String toString() {
			if (next != null) {
				return data + ", " + next;
			} else {
				return (String) data;
			}
		}
	}

	@Override
	public boolean isEmpty() {
		if (first == null) {
			return true;
		}
		return false;
	}

	@Override
	public void addFirst(Object newElement) {
		Node newNode = new Node();
		newNode.data = newElement;

		if (isEmpty()) {
			first = newNode;
			last = newNode;
		} else {
			newNode.next = first;
			first.previous = newNode;
			first = newNode;
		}

	}

	@Override
	public void addLast(Object newElement) {
		Node newNode = new Node();
		newNode.data = newElement;

		if (isEmpty()) {
			first = newNode;
			last = newNode;
		} else {
			newNode.previous = last;
			last.next = newNode;
			last = newNode;
		}
	}

	@Override
	public Object removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Node temp = first;
		first.next.previous = null;
		first = first.next;
		return temp;
	}

	@Override
	public Object removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Node temp = first;
		last.next.previous = null;
		last = last.previous;
		return temp;
	}

	@Override
	public Object getFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return first.data;
	}

	@Override
	public Object getLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return last.data;
	}

	@Override
	public int size() {
		int count = 0;
		Node temp = first;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}
}
