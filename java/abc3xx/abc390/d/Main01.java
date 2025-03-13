import java.util.*;
public class Main {
	static int n;
	static long[] ary;
	static Set<Long> set=new HashSet<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		ary = new long[n];
		for (int i=0; i<n; i++) {
			ary[i] = sc.nextLong();
		}
		List<List<Integer>> groups=new ArrayList<>();
		dfs(0, groups);
//		System.out.println(set);
		System.out.println(set.size());
	}
	static void dfs(int i, List<List<Integer>> groups) {
		if (i==n) {
			long ans=0;
			for (List<Integer> group : groups) {
				long sum=0;
				for (Integer member : group) {
					sum += ary[member];
				}
				ans ^= sum;
			}
//			System.out.println(groups+" "+ans);
			set.add(ans);
			return;
		}
		for (int j=0; j<groups.size(); j++) {
			List<Integer> group=groups.get(j);
			group.add(i);
			dfs(i+1, groups);
			group.remove(group.size()-1);
		}
		List<Integer> group=new ArrayList<>();
		group.add(i);
		groups.add(group);
		dfs(i+1, groups);
		groups.remove(groups.size()-1);
	}
}
/*
3
2 5 7

2
100000000000000000 100000000000000000

6
71 74 45 34 31 60
*/
/*
12
1 2 3 4 5 6 7 8 9 10 11 12
*/
