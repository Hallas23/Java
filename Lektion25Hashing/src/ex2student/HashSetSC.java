package ex2student;


/**
 * HashSetSC implements a hash set using separate chaining. Note: null is not
 * allowed as an element in the set.
 */
public class HashSetSC {
	// the hash table where each entry contains
	// a collection of elements with the same hash value;
	// the collection is implemented as a single linked list
	private Node[] table;
	// the number of elements in the hash set
	private int size;
	
	
	private int[] primtal = {2,3,5,7,11,13,17,19,23,29,31,34,37,41,47,53,59,61,67,71,73,79,83,89,97,101};

	/**
	 * Creates a new HashSetSC with the given table length.
	 */
	public HashSetSC(int tableLength) {
		Node[] emptyTable = new Node[tableLength];
		table = emptyTable;
		size = 0;
	}

	private int hash(Object element) {
		int h = element.hashCode();
		if (h < 0)
			h = -h;
		h = h % table.length;
		return h;
	}

	/**
	 * Returns true, if element is in the set.
	 */
	public boolean contains(Object element) {
		int hash = hash(element);
		Node node = table[hash];
		while (node != null) {
			if (node.data.equals(element)) {
				return true;
			} else {
				node = node.next;
			}
		}

		return false;
	}

	/**
	 * Adds element to this set. Returns true, if element was not already in the
	 * set. Throws IllegalArgumentException, if element is null.
	 */
	public boolean add(Object element) {
		if (contains(element)) {
			return false;
		}
		int hash = hash(element);
		Node node = new Node(element);
		node.next = table[hash];
		table[hash] = node;
		size++;
		if (size / table.length >= 0.7) {
			growIfNeeded();
		}
		return true;
	}
	
	private void growIfNeeded() {
		Node[] table2 = null;
		Node[] temp2 = table;
		for (int i = 0; i < primtal.length; i++) {
			if (table.length == primtal[i]) {
				table2 = new Node[primtal[i + 1]];
			}
		}
		table = table2;
		size = 0;
		for (int i = 0; i < temp2.length; i++) {
			Node node = temp2[i];
			while (node != null) {
				add(node.data);
				node = node.next;
			}
		}
	}

	/**
	 * Removes element from the set. Returns true, if element was removed from the
	 * set.
	 */
	public boolean remove(Object element) {
		int hash = hash(element);
		Node node = table[hash];
		if (table[hash] != null) {
			if (table[hash].data.equals(element)) {
				table[hash] = table[hash].next;
				size--;
				return true;
			} else {
				while (node.next != null) {
					if (node.next.data.equals(element)) {
						node.next = node.next.next;
						size--;
						return true;
					} else {
						node = node.next;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns the number of elements in this set.
	 */
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < table.length; i++) {
			Node node = table[i];
			
			sb.append(i + "[");
			while (node != null) {
				sb.append(node.data + ", ");
				node = node.next;
			}
			
			if (sb.charAt(sb.length() - 1) == ' ') {
				sb.deleteCharAt(sb.length() - 1);
				sb.deleteCharAt(sb.length() - 1);
			}
				
			sb.append("]" + "\n");
		}
		return sb.toString();
	}

	// -------------------------------------------------------------------------

	public class Node {
		private Object data;
		private Node next;

		public Node(Object data) {
			this.data = data;
		}
	}

}
