package queues;

public class CircularArrayEnkeltKædet implements Queue {

		private Node first;
		private int currentSize;
		private int head;
		private int tail;


		public CircularArrayEnkeltKædet() {
			first = null;
			currentSize = 0;
			head = 0;
			tail = 0;
		}

		@Override
		public String toString() {
			return "SortedLinkedList [first=" + first + "]";
		}

		class Node {

			public Object data;
			public Node next;

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
		public void enqueue(Object element) {
			Node newNode = new Node();
			newNode.data = element;
			if (first == null) {
				newNode.next = first;
				first = newNode;
			} else {
				Node temp = first;
				while (temp.next != null) {
					temp = temp.next;
				}
				newNode.next = temp.next;
				temp.next = newNode;
			}

		}

		@Override
		public Object dequeue() {
			Node temp = first;
			if (first != null) {
				first = first.next;
			}
			return temp.data;
		}

		@Override
		public Object getFront() {
			return first.data;
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

