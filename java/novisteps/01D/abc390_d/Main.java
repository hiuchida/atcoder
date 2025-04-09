import java.util.*;
public class Main {
	static int n;
	static long[] ary;
	static List<Long> set=new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		ary = new long[n];
		for (int i=0; i<n; i++) {
			ary[i] = sc.nextLong();
		}
		List<Long> groups=new ArrayList<>();
		dfs(0, groups);
		Collections.sort(set);
//		System.out.println(set);
		List<Long> set2=new ArrayList<>();
		long pre=-1;
		for (int i=0; i<set.size(); i++) {
			long v=set.get(i);
			if (v!=pre) {
				set2.add(v);
				pre=v;
			}
		}
//		System.out.println(set2);
		System.out.println(set2.size());
	}
	static void dfs(int i, List<Long> groups) {
		if (i==n) {
			long ans=0;
			for (Long sum : groups) {
				ans ^= sum;
			}
//			System.out.println(groups+" "+ans);
			set.add(ans);
			return;
		}
		for (int j=0; j<groups.size(); j++) {
			long sum0=groups.get(j);
			long sum = sum0+ary[i];
			groups.set(j, sum);
			dfs(i+1, groups);
			groups.set(j, sum0);
		}
		long sum=ary[i];
		groups.add(sum);
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
