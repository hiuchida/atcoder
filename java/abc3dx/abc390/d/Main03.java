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
		for (int i=set.size()-1; i>0; i--) {
			long v=set.get(i);
			if (v==set.get(i-1)) {
//				System.out.println(i+" "+set.get(i));
				set.remove(i);
			}
		}
//		System.out.println(set);
		System.out.println(set.size());
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

[0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78]
40
*/
