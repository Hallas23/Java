package ex2student;

public class TestApp {

	public static void main(String[] args) {
		HashSetSC hs = new HashSetSC(10);
		hs.add("Christian");
		hs.add("Jørgen");
		hs.add("Hej");
		hs.add("Ole");
		hs.add("Hens");
		
		hs.remove("Ole");
		
		System.out.println(hs);

		
	}

}
