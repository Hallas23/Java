package opgave.ex1student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Ex1 {

	public static void main(String[] args) {
		List<Person> list = List.of(
				new Person("Bent", 25), new Person("Susan", 34),
				new Person("Mikael", 60), new Person("Klaus", 44),
				new Person("Birgitte", 17), new Person("Liselotte", 9));
		List<Person> persons = new ArrayList<Person>(list);
		System.out.println(persons);
		System.out.println();
		
		Person f44 = findFirst(persons, p -> p.getAge() == 44);
		System.out.println(f44);
		
		Person fS = findFirst(persons, p -> p.getName().indexOf('S') >= 0);
		System.out.println(fS);
		
		Person fI = findFirst(persons, p -> p.getName().indexOf('i') != p.getName().lastIndexOf('i'));
		System.out.println(fI);
		
		Person fEqual = findFirst(persons, p -> p.getName().length() == p.getAge());
		System.out.println(fEqual);
		
		List<Person> findAlli = findAll(persons, p -> p.getName().indexOf('i') >= 0);
		System.out.println(findAlli);
		
		List<Person> findFirstS = findAll(persons, p -> p.getName().charAt(0) == 'S');
		System.out.println(findFirstS);
	
		List<Person> findLength5 = findAll(persons, p -> p.getName().length() == 5);
		System.out.println(findLength5);
		
		List<Person> findLengthatleast6andagebelow40 = findAll(persons, p -> p.getName().length() >= 6 && p.getAge() < 40);
		System.out.println(findLengthatleast6andagebelow40);
		
		
		
		
		
		
		
	}
	
	/**
	 * Returns from the list the first person
	 * that satisfies the predicate.
	 * Returns null, if no person satisfies the predicate.
	 */
	public static Person findFirst(List<Person> list, Predicate<Person> filter) {
		for (Person p : list) {
			if (filter.test(p))
				return p;
		}
		return null;
	}
	
	public static List<Person> findAll(List<Person> list, Predicate<Person> filter) {
		
		List<Person> personsnew = new ArrayList<Person>();
		
		for (Person p : list) {
			if (filter.test(p))
				 personsnew.add(p);
		}
		return personsnew;
	}
}
