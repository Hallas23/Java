package opgave4e;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ex4 {

	public static void main(String[] args) {
		List<Runner> runners = new ArrayList<>();
		runners.addAll(List.of(
				new Runner("Ib", 30),
				new Runner("Per", 50),
				new Runner("Ole", 27),
				new Runner("Ulla", 40),
				new Runner("Jens", 35),
				new Runner("Hans", 28)));
		System.out.println(runners);
		System.out.println();
		
		Iterator<Runner> it = runners.iterator();
		while(it.hasNext()) {
			Runner r = it.next();
			
			if (r.getLapTime() >= 40) {
				it.remove();
			}
		}
		
		System.out.println(runners);
		System.out.println();

	}
}