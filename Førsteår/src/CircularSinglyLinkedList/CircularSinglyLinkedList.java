package CircularSinglyLinkedList;

import java.util.Random;

public class CircularSinglyLinkedList {

	private int size = 0;
	private Node head = null;
	private Node tail = null;

	// add a new node at the start of the linked list
	public void addNodeAtStart(int data) {
		System.out.println("Adding node " + data + " at start");
		Node n = new Node(data);
		if (size == 0) {
			head = n;
			tail = n;
			n.next = head;
		} else {
			Node temp = head;
			n.next = temp;
			head = n;
			tail.next = head;
		}
		size++;
	}

	public void addNodeAtEnd(int data) {
		if (size == 0) {
			addNodeAtStart(data);
		} else {
			Node n = new Node(data);
			tail.next = n;
			tail = n;
			tail.next = head;
			size++;
		}
		System.out.println("\nNode " + data + " is added at the end of the list");
	}

	public void deleteNodeFromStart() {
		if (size == 0) {
			System.out.println("\nList is Empty");
		} else {
			System.out.println("\ndeleting node " + head.data + " from start");
			head = head.next;
			tail.next = head;
			size--;
		}
	}

	public int elementAt(int index) {
		if (index > size) {
			return -1;
		}
		Node n = head;
		while (index - 1 != 0) {
			n = n.next;
			index--;
		}
		return n.data;
	}

	public void pickspot(int n) {
		Node temp = head;
		for (int i = 0; i < n; i++) {
			temp = temp.next;
		}
		head = temp;
	}

	public void eatPerson(int n) {
		if (n == 0) {
			deleteNodeFromStart();
		}
		
		if (n != 0) {
			Node temp = head;
			for (int i = 0; i < n - 1; i++) {
				temp = temp.next;
			}
			if (n == size - 1) {
				System.out.println(temp.data);
				
				temp.next = temp.next.next;
				tail = temp;
			} else {
				temp.next = temp.next.next;
				size--;
			}
		}
	}

	// print the linked list
	public void print() {
		System.out.print("Circular Linked List:");
		Node temp = head;
		if (size <= 0) {
			System.out.print("List is empty");
		} else {
			do {
				System.out.print(" " + temp.data);
				temp = temp.next;
			} while (temp != head);
		}
		System.out.println();
	}

	// get Size
	public int getSize() {
		return size;
	}

	public static void main(String[] args) {
		CircularSinglyLinkedList c = new CircularSinglyLinkedList();
		c.addNodeAtStart(3);
		c.addNodeAtStart(2);
		c.addNodeAtStart(1);
		c.print();
		c.eatPerson(2);
		c.print();
		c.eatPerson(1);
		c.print();
//        c.addNodeAtEnd(4);
//        c.print();
//        System.out.println("Size of linked list: "+ c.getSize());
//        System.out.println("Element at 2nd position: "+ c.elementAt(2));
	}

}

class Node {
	int data;
	Node next;

	public Node(int data) {
		this.data = data;
	}
}