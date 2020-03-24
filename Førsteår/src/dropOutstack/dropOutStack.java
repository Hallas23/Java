package dropOutstack;

import java.util.LinkedList;

public class dropOutStack implements Stack2 {
	private Node top;
	private Node bottom;

	private int size;
	private final int n = 5;
	
	public dropOutStack() {
		top = null;
		bottom = null;
	}

	@Override
	public void push(Object data) {
		Node newNode = new Node();
		newNode.data = data;
		
		if (size == 0) {
			bottom = newNode;
			newNode.next = top;
			top = newNode;
		}
		
		if (size < n && size > 0) {
			newNode.next = top;
			top = newNode;
		}
	}

	@Override
	public Object pop() {
		if (top != null) {
			return top.previous = top;
		} else {
			return null;
// throw EmptyStackException();
		}
	}

	public void displayStack() {

	}

	@Override
	public Object peek() {
		return top.data;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public int size() {
		return size;
	}

	public static void main(String[] arguments) {
		dropOutStack stack = new dropOutStack();
		stack.push("Test");
		stack.displayStack();
	}
	class Node {
		public Object data;
		public Node next;
		public Node previous;
	}
}
