package opgave4e;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import opgave.ex1student.Person;
import opgave2.Runner;

public class Opgave4c {

	public static void main(String[] args) {
		List<Runner> runners = new ArrayList<>();
		runners.addAll(List.of(new Runner("Ib", 30), new Runner("Per", 50), new Runner("Ole", 27),
				new Runner("Ulla", 40), new Runner("Jens", 35), new Runner("Hans", 28)));
		System.out.println(runners);
		System.out.println();

		runners.removeIf((Runner r1) -> r1.getLapTime() >= 40);
		
		System.out.println(runners);
		System.out.println();

	}
}