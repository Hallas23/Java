package opgave2;

public class test {

	public static void main(String[] args) {
		faelles f = new faelles();
		
		f.tagerRandomTid(1111);
		
		myThread t1 = new myThread("Tråd1", f);
		myThread t2 = new myThread("Tråd2", f);
		
		
		
		t1.start();
		t2.start();
	}
}
