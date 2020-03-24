package Threads;

public class myThread2 extends Thread	{
	
	
	public myThread2 () {
		
	}
	
	public void run() {
		while (true) {
			System.out.println(string.s);
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
