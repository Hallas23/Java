package opgave1;

public class TestApp {

	public static void main(String[] args) {
		LinkedList ll1 = new LinkedList();
		ll1.addElement("eej");
		ll1.addElement("dle");
		ll1.addElement("aichelle");
		ll1.addElement("cchelle");
		ll1.addElement("bej");
		

		System.out.println(ll1);
		System.out.println(ll1.removeElement("aichelle"));
		System.out.println(ll1);
		System.out.println(ll1.countElements());
	}

}
