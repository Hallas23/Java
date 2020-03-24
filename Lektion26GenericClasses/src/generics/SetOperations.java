package generics;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetOperations {

	public static <T> Set<T> union(Set<T> s1, Set<T> s2) {
		Set<T> nyliste = new HashSet<T>();
		nyliste.addAll(s1);
		nyliste.addAll(s2);
		return nyliste;
	}

	public static <T> Set<T> differens(Set<T> s1, Set<T> s2) {
		Set<T> nyliste = new HashSet<T>();
		Iterator<T> it = s1.iterator();

		while (it.hasNext()) {
			T temp = it.next();
			if (!s2.contains(temp)) {
				nyliste.add(temp);
			}
		}
		return nyliste;

	}

	public static <T> Set<T> intesection(Set<T> s1, Set<T> s2) {
		Set<T> nyliste = new HashSet<T>();
		Iterator<T> it = s1.iterator();

		while (it.hasNext()) {
			T temp = it.next();
			if (s2.contains(temp)) {
				nyliste.add(temp);
			}
		}
		return nyliste;
	}

}
