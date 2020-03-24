package opgave2;

public class QueueDemo {
	public static void main(String[] args) {
		DictionaryClass<Integer,String> d = new DictionaryClass();
		d.put(1,("Ole"));
		d.put(2,("Bole"));
		d.put(3,("Skole"));
		d.put(4,("Mole"));
		d.put(4, "Kens");
		
		System.out.println(d.put(4, "Hej"));
		
		d.put(2, "Heks");
		
		System.out.println(d.remove(4));
		
		
		System.out.println(d);
		
	}
}
