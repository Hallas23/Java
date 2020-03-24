package dropOutstack;



public interface Stack<T> {
    public void push(T element);
    public T pop();
    public T peek();
	void clear();
	boolean isEmpty();
	int size();
}
