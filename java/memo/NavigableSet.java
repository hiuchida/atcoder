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
/*
ceil:i<=c
flor:i>=c
high:i<c
 low:i>c

 org:[1, 6, 11, 16, 21, 26, 31, 36, 41, 46, 51, 56, 61, 66, 71, 76, 81, 86, 91, 96]
ceil:[1:1, 5:6, 9:11, 13:16, 17:21, 21:21, 25:26, 29:31, 33:36, 37:41, 41:41, 45:46, 49:51, 53:56, 57:61, 61:61, 65:66, 69:71, 73:76, 77:81, 81:81, 85:86, 89:91, 93:96, 97:null]
flor:[1:1, 5:1, 9:6, 13:11, 17:16, 21:21, 25:21, 29:26, 33:31, 37:36, 41:41, 45:41, 49:46, 53:51, 57:56, 61:61, 65:61, 69:66, 73:71, 77:76, 81:81, 85:81, 89:86, 93:91, 97:96]
high:[1:6, 5:6, 9:11, 13:16, 17:21, 21:26, 25:26, 29:31, 33:36, 37:41, 41:46, 45:46, 49:51, 53:56, 57:61, 61:66, 65:66, 69:71, 73:76, 77:81, 81:86, 85:86, 89:91, 93:96, 97:null]
 low:[1:null, 5:1, 9:6, 13:11, 17:16, 21:16, 25:21, 29:26, 33:31, 37:36, 41:36, 45:41, 49:46, 53:51, 57:56, 61:56, 65:61, 69:66, 73:71, 77:76, 81:76, 85:81, 89:86, 93:91, 97:96]
head:[1, 6, 11, 16, 21, 26]
 sub:[31, 36, 41, 46, 51, 56, 61, 66]
tail:[71, 76, 81, 86, 91, 96]
sub2:[31, 36, 41, 46, 51, 56, 61, 66, 71]
*/
