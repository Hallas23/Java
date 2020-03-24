package towers;

public class Towerdemo {

	public static void main(String[] args) {
		move(10, 1, 3);
	}

	public static void move(int n, int from, int to) {
		if	(n == 1) {
			System.out.println("Move: " + from + " -> " + to);
		}	else {
			int third = 6 - from - to;
				move(n-1, from, third);
			System.out.println("Move: " + from + " -> " + to);
				move(n-1, third, to);
		}
	}
}
