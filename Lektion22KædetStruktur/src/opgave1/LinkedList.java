package opgave1;

public class LinkedList {

	private Node first;

	public LinkedList() {
		first = null;
	}

	public void addElement(String element) {
		Node newNode = new Node();
		newNode.data = element;
		if (first == null || first.data.compareTo(element) >= 0) {
			newNode.next = first;
			first = newNode;

		} else {
			Node temp = first;
			while (temp.next != null && temp.next.data.compareTo(element) <= 0) {
				temp = temp.next;
			}
			newNode.next = temp.next;
			temp.next = newNode;
		}

	}

	public boolean removeElement(String element) {
		boolean fjernet = false;
		if (first != null) {
			if (first.data.equals(element)) {
				first = first.next;
			} else {
				Node temp = first;
				boolean found = false;
				while (!found && temp.next != null) {
					if (temp.next.data.equals(element)) {
						found = true;
					} else {
						temp = temp.next;
					}
				}
				if (found) {
					Node temp2 = temp.next;
					temp.next = temp2.next;
					temp2.next = null;
					fjernet = true;

				}
			}
		}
		return fjernet;

	}

	public int countElements() {
		int count = 0;
		Node temp = first;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}
	
	@Override
	public String toString() {
		return "SortedLinkedList [first=" + first + "]";
	}

	class Node {

		public String data;
		public Node next;

		@Override
		public String toString() {
			if (next != null) {
				return data + ", " + next;
			} else {
				return data;
			}

		}

	}

}