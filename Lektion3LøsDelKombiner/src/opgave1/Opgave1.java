package opgave1;

import java.util.ArrayList;

public class Opgave1 {

	public static void main(String[] args) {
		ArrayList<Integer> al1 = new ArrayList<>();
		al1.add(3);
		al1.add(2);
		al1.add(5);

		System.out.println(sum(al1));
		System.out.println(countZero(al1));
		System.out.println(al1);
		mergesort(al1);
		System.out.println(al1);

	}

	public static int sum(ArrayList<Integer> list) {
		return sum(list, 0, list.size() - 1);
	}

	private static int sum(ArrayList<Integer> list, int l, int h) {

		if (l == h) {
			return list.get(l);
		} else {
			int m = (l + h) / 2;
			int sum2 = sum(list, l, m);
			int sum3 = sum(list, m + 1, h);
			return sum2 + sum3;
		}

	}

	public static int countZero(ArrayList<Integer> list) {
		return countZero(list, 0, list.size() - 1);
	}

	private static int countZero(ArrayList<Integer> list, int l, int h) {
		if (l == h) {
			if (list.get(l) == 0) {
				return 1;
			} else {
				return 0;
			}
		} else {
			int m = (l + h) / 2;
			int zero1 = countZero(list, l, m);
			int zero2 = countZero(list, m + 1, h);

			return zero1 + zero2;
		}
	}

	private static void mergesort(ArrayList<Integer> list, int l, int h) {
		if (l < h) {
			int m = (l + h) / 2;
			mergesort(list, l, m);
			mergesort(list, m + 1, h);
			merge(list, l, m, h);
		}
	}

	public static void mergesort(ArrayList<Integer> list) {
		mergesort(list, 0, list.size() - 1);
	}

	private static void merge(ArrayList<Integer> list, int l, int m, int h) {
		ArrayList<Integer> temp = new ArrayList<>();
		
		int i1 = l;
		int i2 = m;
		int left;
		int right;

		while (i1 <= m && i2 < h) {
			if (s1.get(i2).compareTo(s2.get(i3)) <= 0) {
				temp.add(s1.get(i2));
				i2++;
			} else {
				temp.add(s2.get(i3));
				i3++;
			}
		}
		while (i2 < s1.size()) {
			temp.add(s1.get(i2));
			i2++;
		}
		while (i3 < s2.size()) {
			temp.add(s2.get(i3));
			i3++;
		}
		return temp;
	}
}
