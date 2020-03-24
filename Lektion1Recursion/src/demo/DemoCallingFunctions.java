package demo;

public class DemoCallingFunctions {

	public static void main(String[] args) {
		for (int n = 0; n < 50; n++) {
			System.out.printf("%d  %,13d\n", n, fib(n));
		}
	}

	public static int fib(int n) {
		if (n == 0 || n == 1)
			return 1;
		else
			return fib(n - 1) + fib(n - 2);
	}

	public static void f(int n) {
		System.out.println("f(" + n + ")");
		g(n - 1);
	}

	public static void g(int n) {
		System.out.println("g(" + n + ")");
		if (n > 0)
			g(n - 1);
	}

	public static void gIt(int n) {
		for (int i = 8; i >= 0; i--) {
			System.out.println("g(" + i + ")");
		}
	}
}
