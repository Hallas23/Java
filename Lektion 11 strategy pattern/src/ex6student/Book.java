package ex6student;

public class Book {
	private String title;
	private int count;

	public Book(String title) {
		this.title = title;
		this.count = 0;
	}

	public String getTitle() {
		return title;
	}

	public int getCount() {
		return count;
	}

	public void incCount(int amount) {
		count += amount;
	}

	public void decCount(int amount) {
		count -= amount;
	}

	@Override
	public String toString() {
		return title + "(" + count + ")";
	}
}
