package opgave1;

import java.util.HashSet;
import java.util.Set;

public class Opgave1 {

	public static void main(String[] args) {
		Set<Integer> s1 = new HashSet<>();
		s1.add(34);
		s1.add(12);
		s1.add(23);
		s1.add(45);

		s1.add(34);
		s1.add(98);
		s1.add(28);

		
		System.out.println(s1);
		int count = 0;
		for (Integer set: s1) {
			count++;
		}
		System.out.println(count);
	}
}
