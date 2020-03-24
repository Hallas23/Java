package opgave3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import opgave2.Runner;

public class Opgave3 {

	public static void main(String[] args) {

		
//		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
//		
//		Iterator<Integer> it = list.iterator();
//		
//		while (it.hasNext()) {
//			int tal = (Integer) it.next();
//				System.out.println(tal);
//		}
		
		final Map<Integer,Integer> ints = new HashMap<>();
		ints.put(2, 4);
		ints.put(3, 9);
		ints.put(4, 16);
		ints.put(5, 25);
		ints.put(6, 36);
		
		Iterator<Entry<Integer, Integer>> it2 = ints.entrySet().iterator();
		
		while (it2.hasNext()) {
			Map.Entry pair = (Map.Entry)it2.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			it2.remove();
		}

	}

}
