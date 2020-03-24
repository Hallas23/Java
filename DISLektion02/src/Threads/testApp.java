package Threads;

public class testApp {
	
	
	
	public static void main(String[] args) { 
		myThread thread1 = new myThread();
		myThread2 thread2 = new myThread2();
		
		thread1.start();
		thread2.start();
	}
}
