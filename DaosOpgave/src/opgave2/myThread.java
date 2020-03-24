package opgave2;

public class myThread extends Thread{
	
	private String navn;
	private faelles faelles;
	
	public myThread(String navn, faelles faelles ) {
		this.faelles = faelles;
		this.navn = navn;
		
	}
	
	public void run() {
		for(int j = 0; j < 100; j++) {
			faelles.kritisksection();
			faelles.tagerRandomTid(j);
		}
		System.out.println(faelles.getGlobal());
	}
	
	

}