import java.util.*;
public class Main {
	static TreeMap<Integer,TreeSet<Integer>> mapl=new TreeMap<>();
	static TreeMap<Integer,TreeSet<Integer>> mapr=new TreeMap<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ax=new int[n];
		int[] ay=new int[n];
		for (int i=0; i<n; i++) {
			ax[i]=sc.nextInt();
			ay[i]=sc.nextInt();
		}
		String s = sc.next();
		for (int i=0; i<n; i++) {
			if (s.charAt(i)=='L') {
				add(mapl, ay[i], ax[i]);
			} else {
				add(mapr, ay[i], ax[i]);
			}
		}
//		System.out.println(Arrays.toString(ax));
//		System.out.println(Arrays.toString(ay));
//		System.out.println(mapl);
//		System.out.println(mapr);
		for (int ly : mapl.keySet()) {
			if (!mapr.containsKey(ly)) continue;
			check(ly);
		}
		System.out.println("No");
	}
	static void add(TreeMap<Integer,TreeSet<Integer>> map, int y, int x) {
		TreeSet<Integer> set=map.get(y);
		if (set==null) {
			set=new TreeSet<>();
			map.put(y, set);
		}
		set.add(x);
	}
	static void check(int y) {
		TreeSet<Integer> setl=mapl.get(y);
		TreeSet<Integer> setr=mapr.get(y);
		for (int lx : setl) {
			Integer rx=setr.lower(lx);
			if (rx!=null) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
	}
}
/*
3
2 3
1 1
4 1
RRL

2
1 1
2 1
RR

10
1 3
1 4
0 0
0 2
0 4
3 1
2 4
4 2
4 4
3 3
RLRRRLRLRR
*/
