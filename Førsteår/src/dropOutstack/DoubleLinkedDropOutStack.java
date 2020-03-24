package dropOutstack;



public class DoubleLinkedDropOutStack<T> implements Stack<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;
	private int maxSize;

	private static final int DEFAUL_MAX_SIZE = 5;

	public DoubleLinkedDropOutStack() {
		this(DEFAUL_MAX_SIZE);
	}

	public DoubleLinkedDropOutStack(int maxSize) {
		this.head = new Node<T>();
		this.tail = new Node<T>();
		head.next = tail;
		tail.previous = head;
		this.maxSize = maxSize;
		size = 0;

	}

	private boolean isFull() {
		return maxSize == size;
	}

	private void popBottom() {
		tail.previous.previous.next = tail;
		tail.previous = tail.previous.previous;
		size--;
	}

	@Override
	public void push(T newEntry) {

		if (isFull()) {
			popBottom();
		}
		Node<T> toAdd = new Node<T>(newEntry, head.next, head);
		head.next.previous = toAdd;
		head.next = toAdd;
		size++;
	}

	@Override
	public T pop() {
		if (isEmpty())
			return null;

		T popped = head.next.data;
		head.next.next.previous = head;
		head.next = head.next.next;
		size--;
		return popped;
	}

	@Override
	public T peek() {
		return isEmpty() ? null : head.next.data;
	}

	@Override
	public boolean isEmpty() {
		return head.next == tail && tail.previous == head;
	}

	@Override
	public void clear() {
		head = new Node<T>();
		tail = new Node<T>();
		tail.previous = head;
		head.next = tail;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}


	// if not static - the inner class can use the instance fields from the
	// outer class and the T
	private static class Node<T> {

		// all fields are visible to the outer class
		private T data; // entry in bag
		private Node<T> next; // link to next node
		private Node<T> previous; // link to the previous node

		public Node() {
			this.data = null;
			this.next = null;
			this.previous = null;
		}

		public Node(T data, Node<T> next, Node<T> previous) {
			this.data = data;
			this.next = next;
			this.previous = previous;
		}

	} // end Node<T> class
	
	public static void main(String[] arguments) {
		DoubleLinkedDropOutStack<Integer> stack = new DoubleLinkedDropOutStack<Integer>(3);
		stack.push(1);
		stack.push(10);
		stack.push(100);
		System.out.println(stack.pop());
		System.out.println(stack.size);
		
	}
}
