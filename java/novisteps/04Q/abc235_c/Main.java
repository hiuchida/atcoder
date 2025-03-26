import java.util.*;
public class Main {
	static TreeMap<Integer, List<Integer>> map=new TreeMap<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int[] ax=new int[q];
		int[] ak=new int[q];
		TreeSet<Integer> set2=new TreeSet<>();
		for (int i=0; i<q; i++) {
			ax[i] = sc.nextInt();
			ak[i] = sc.nextInt();
			set2.add(ax[i]);
		}
		for (int i=0; i<n; i++) {
			int a=ary[i];
			if (set2.contains(a)) {
				add(i+1, a);
			}
//			System.out.println(map);
		}
		for (int i=0; i<q; i++) {
			int x=ax[i];
			int k=ak[i];
			List<Integer> set=map.get(x);
			if (set==null || set.size()<k) {
				System.out.println(-1);
			} else {
				System.out.println(set.get(k-1));
			}
		}
	}
	static void add(int i, int a) {
		List<Integer> set=map.get(a);
		if (set==null) {
			set=new ArrayList<>();
			map.put(a, set);
		}
		set.add(i);
	}
}
/*
6 8
1 1 2 3 1 2
1 1
1 2
1 3
1 4
2 1
2 2
2 3
4 1

3 2
0 1000000000 999999999
1000000000 1
123456789 1
*/
