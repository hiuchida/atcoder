import java.util.*;
public class Main {
	public static void main(String[] args) {
		NavigableSet<Integer> set = new TreeSet<>();
		for (int i = 1; i < 100; i += 5) {
			set.add(i);
		}
		System.out.println(" org:" + set);
		
		List<String> list = new ArrayList<>();
		for (int i = 1; i < 100; i+=4) {
			Integer c = set.ceiling(i);
			list.add(i + ":" + c);
		}
		System.out.println("ceil:" + list);

		list = new ArrayList<>();
		for (int i = 1; i < 100; i+=4) {
			Integer f = set.floor(i);
			list.add(i + ":" + f);
		}
		System.out.println("flor:" + list);

		list = new ArrayList<>();
		for (int i = 1; i < 100; i+=4) {
			Integer h = set.higher(i);
			list.add(i + ":" + h);
		}
		System.out.println("high:" + list);

		list = new ArrayList<>();
		for (int i = 1; i < 100; i+=4) {
			Integer l = set.lower(i);
			list.add(i + ":" + l);
		}
		System.out.println(" low:" + list);

		SortedSet<Integer> head = set.headSet(31);
		System.out.println("head:" + head);
		SortedSet<Integer> sub = set.subSet(31, 71);
		System.out.println(" sub:" + sub);
		SortedSet<Integer> tail = set.tailSet(71);
		System.out.println("tail:" + tail);
		SortedSet<Integer> sub2 = set.subSet(31, true, 71, true);
		System.out.println("sub2:" + sub2);
	}
}
