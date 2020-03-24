package opgave3;

import java.util.Arrays;
import java.util.Map;

public class DictionaryHashMap<K, V> implements Dictionary<K, V> {

	private java.util.Map<Object, Object>[] tabel;
	private int N = 10;
	private int size;

	/**
	 * HashingMap constructor comment.
	 */
	public DictionaryHashMap() {
		tabel = new java.util.HashMap[N];
		for (int i = 0; i < N; i++) {
			tabel[i] = new java.util.HashMap<Object, Object>();
		}
		size = 0;
	}

	@Override
	public V get(K key) {
		int i = key.hashCode() % N;
		java.util.Map<Object, Object> map = tabel[i];
		return (V) map.get(key);
	}

	@Override
	public boolean isEmpty() {
		boolean empty = true;
		int i = 0;
		while (empty && i < N) {
			empty = (tabel[i]).isEmpty();
			i++;
		}
		return empty;
	}

	@Override
	public V put(K key, V value) {
		if (key == null && value == null) {
			throw new IllegalArgumentException();
		}
		int i = key.hashCode() % N;
		if (tabel[i].isEmpty())
			size++;
		tabel[i] = (Map<Object, Object>) value;
		return value;
	}

	@Override
	public V remove(K key) {
		int i = key.hashCode() % N;
			V temp = (V) tabel[i];
			tabel[i] = null;
			return temp;
		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return "DictionaryHashMap [tabel=" + Arrays.toString(tabel) + ", size=" + size + "]";
	}
	

}
