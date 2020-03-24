package BST;

public class test {

	public static void main(String[] args) {
		BinarySearchTree<Integer> t = new BinarySearchTree<>();
		
		t.add(45);
		t.add(77);
		t.add(90);
		t.add(88);
		t.add(46);
		
		t.print();
		System.out.println(t.removeMin());
		System.out.println();
		System.out.println(t.removeMin());
		System.out.println();
		System.out.println(t.removeMin());
		System.out.println();
		t.print();
	}

}
