package example;

import java.util.NoSuchElementException;

public class ArrayStack<T> implements Stack<T> {
    private int size = 0;
    private T[] stack;
    
    public ArrayStack() {
        this.stack = (T[]) new Object[2];
    }
    
    @Override
    public void push(T element) {
        stack[size++] = element;
    }

    @Override
    public T pop() {
        if (size < 1) { throw new NoSuchElementException(); }
        T element = stack[--size];
        stack[size] = null;
        return element;
    }
    
    @Override
    public T peek() {
        if (size < 1) { throw new NoSuchElementException(); }
        return stack[size - 1];
    }
    
    @Override
    public boolean empty() {
        return size == 0;
    }
    
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result = stack[i] + " " + result;
        }
        return result.trim();
    }

}
