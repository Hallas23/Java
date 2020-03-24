package arrayExercise1;

import java.util.Arrays;

import queues.Queue;

/**
 * An implementation of a queue using an array
 */
public class ArrayQueue implements Queue {
	private int counter;
	Object[] arr;
	
	/**
	 * Constructs an empty queue.
	 */
	public ArrayQueue() {
		arr = new Object[10];
		counter = 0;

	}

	/**
	 * Checks whether this queue is empty.
	 * @return true if this queue is empty
	 */
	@Override
	public boolean isEmpty() {
		if (counter == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Adds an element to the tail of this queue.
	 * @param newElement the element to add
	 */
	@Override
	public void enqueue(Object newElement) {
		if (counter == arr.length) {
			arr = Arrays.copyOf(arr, arr.length * 2);
		}
		arr[counter] = newElement;
		counter++;
	}

	/**
	 * Removes an element from the head of this queue.
	 * @return the removed element
	 */
	@Override
	public Object dequeue() {
		Object temp = arr[0];
		for (int i = 0; i < counter - 1; i++) {
			arr[i] = arr[i + 1];
		}		
		counter--;
		return temp;
	}

	/**
	 * Returns the head of this queue. The queue is unchanged.
	 * @return the head element
	 */
	@Override
	public Object getFront() {
		return arr[0];
	}

	/**
	 * The number of elements on the queue.
	 * @return the number of elements in the queue
	 */
	@Override
	public int size() {
		return counter;
	}
}
