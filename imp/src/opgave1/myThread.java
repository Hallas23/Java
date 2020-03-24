package opgave1;

public class myThread extends Thread{
	
	private String navn;
	private faelles faelles;
	
	public myThread(String navn, faelles faelles ) {
		this.faelles = faelles;
		this.navn = navn;
		
	}
	
	public void run() {
		for(int j = 1; j < 100; j++) {
			faelles.kritisksection();
			faelles.tagerRandomTid(j);
		}
		System.out.println(faelles.getGlobal());
	}
	
	

}