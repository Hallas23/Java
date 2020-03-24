package opgave3;

public interface Dictionary<K, V> {

	public V get(K key);
	
	public boolean isEmpty();
	
	public V put(K key, V Value);
	
	public V remove(K key);
	
	public int size();
}
