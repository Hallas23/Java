package exercises;

import java.util.Arrays;

public class ArrayStack<T> implements Stack<T> {

	Object[] arr = new Object[10];
	private int counter = 0;

	@Override
	public void push(Object element) {
		if (counter == arr.length) {
			arr = Arrays.copyOf(arr, arr.length * 2);
		}
		arr[counter] = element;
		counter++;
	}

	@Override
	public Object pop() {
		if (counter > 0) {
			Object temp = arr[counter];
			arr[counter] = null;
			counter--;
			return temp;

		} else {
			return null;
		}
	}

	public void displayStack() {
		System.out.println(arr.toString());
	}

	public Object peek() {
		if (counter > 0) {
			return arr[counter - 1];
		} else {
			return null;
		}
	}

	public boolean isEmpty() {
		if (counter <= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		return counter;
	}

	public static void main(String[] arguments) {
		ArrayStack<String> stack = new ArrayStack<>();
		stack.push("Hej");
		stack.push("Ole");
		stack.push("Jens");
		
		System.out.println(stack.toString());
		stack.pop();
		System.out.println(stack.toString());

	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < counter; i++) {
			s += arr[i].toString() + " " + "\n";
		}
		return s;
	}

	public int getCounter() {
		return counter;
	}

	public boolean checkParenteses(String s) {
		ArrayStack<T> curlyStack = new ArrayStack<T>();
		ArrayStack<T> bracketStack = new ArrayStack<T>();
		ArrayStack<T> parentesStack = new ArrayStack<T>();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				parentesStack.push('(');
			}
			if (s.charAt(i) == ')') {
				if (parentesStack.pop() == null) {
					return false;
				}
			}
			if (s.charAt(i) == '{') {
				curlyStack.push(s.charAt(i));
			}
			if (s.charAt(i) == '}') {
				if (curlyStack.pop() == null) {
					return false;
				}
			}
			if (s.charAt(i) == '[') {
				bracketStack.push(s.charAt(i));
			}
			if (s.charAt(i) == ']') {
				if (bracketStack.pop() == null) {
					return false;
				}
			}
		}

		if (parentesStack.size() == 0 && curlyStack.size() == 0 && bracketStack.size() == 0)
			return true;

		return false;
	}
}