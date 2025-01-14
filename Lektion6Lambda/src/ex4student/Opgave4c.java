package ex4student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import opgave.ex1student.Person;

public class Opgave4c {

	public static void main(String[] args) {
		List<Runner> runners = new ArrayList<>();
		runners.addAll(List.of(new Runner("Ib", 30), new Runner("Per", 50), new Runner("Ole", 27),
				new Runner("Ulla", 40), new Runner("Jens", 35), new Runner("Hans", 28)));
		System.out.println(runners);
		System.out.println();

		boolean RemoveifRunnerover40 = removeIf(runners, r -> r.getLapTime() >= 40);
		System.out.println(RemoveifRunnerover40);

		System.out.println(runners);
		System.out.println();

	}

	public static boolean removeIf(List<Runner> runners, Predicate<Runner> filter) {
		boolean runner = false;
		Iterator<Runner> it = runners.iterator();
		
		while (it.hasNext()) {
			Runner r = it.next();

			if (filter.test(r)) {
				it.remove();
				runner = true;
			}
		}
		return runner;

	}
}
