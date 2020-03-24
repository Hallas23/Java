package opgave2;




public class DictionaryClass<K, V> implements Dictionary<K, V> {
	
	private Node[] table;
	private int size;;
	
	public DictionaryClass() {
		table = new Node[10];
		size = 0;
	}
	
	private int hash(K key) {
		int h = key.hashCode();
		if (h < 0)
			h = -h;
		h = h % table.length;
		return h;
	}

	@Override
	public V get(K key) {
		int hash = hash(key);
		Node node = table[hash];
		while (node != null) {
			if (node.key.equals(key)) {
				return (V) node;	
			}
			else {
				node = node.next;
			}
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < table.length; i++) {
			Node node = table[i];
				if (node != null)
					return false;
		
		}
		return true;
	}

	@Override
	public V put(K key, V Value) {
		if (key == null && Value == null) {
			System.out.println("Key eller Value må ikke være null");
		}
		if (get(key) != null) {
			Node temp = table[hash(key)];
			while (temp != null) {
				if (temp.key.equals(key)) {
					temp.value = Value;
					return (V) temp.value;
				}
				else {
					temp = temp.next;
				}
			}
		}
		int hash = hash(key);
		Node node = new Node(key, Value);
		node.next = table[hash];
		table[hash] = node;
		size++;
		
		return (V) node.value;
	}

	@Override
	public V remove(K key) {
		int h = hash(key);
		Node n = table[h];
		V value;
		if(get(key)!=null) {
			if(n.key.equals(key)) {
				 value = (V) n.value;
				 table[h] = null;
				 size--;
				 return value;
			}
			else {
				while(!n.next.key.equals(key)) {
					n = n.next;
				}
				value =  (V) n.next.value;
				n.next=n.next.next;
				size--;
				return value;
			}
		}
		return null;
	}

	@Override
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
				sb.append(node.value + ", ");
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
	
	public class Node<K, V> {
		private K key;
		private V value;
		private Node next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

}
