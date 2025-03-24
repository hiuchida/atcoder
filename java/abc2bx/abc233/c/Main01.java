import java.util.*;
public class Main {
	static int n;
	static long x;
	static List<List<Integer>> list;
	static long ans=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		x = sc.nextLong();
		list=new ArrayList<>();
		for (int i=0; i<n; i++) {
			int l = sc.nextInt();
			List<Integer> list0=new ArrayList<>();
			for (int j=0; j<l; j++) {
				int a = sc.nextInt();
				if (x/a*a==x) list0.add(a);
			}
			Collections.sort(list0);
			list.add(list0);
		}
//		System.out.println(list);
		List<Long> set=new ArrayList<>();
		for (long j : list.get(0)) {
			set.add(j);
		}
		dfs(1, set);
		System.out.println(ans);
	}
	static void dfs(int i, List<Long> set) {
//		System.out.println(i+" "+set);
		if (i==n) {
			for (long k : set) {
				if (k==x) ans++;
			}			
			return;
		}
		List<Long> set2=new ArrayList<>();
		List<Integer> list0=list.get(i);
		for (int j : list0) {
			for (long k : set) {
				long y=j*k;
				if (y<=x && x/y*y==x) set2.add(j*k);
			}
		}
		dfs(i+1, set2);
	}
}
/*
2 40
3 1 8 4
2 10 5

3 200
3 10 10 10
3 10 10 10
5 2 2 2 2 2

3 1000000000000000000
2 1000000000 1000000000
2 1000000000 1000000000
2 1000000000 1000000000
*/
