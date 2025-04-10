import java.util.*;
public class Main {
	static int n;
	static long x;
	static List<List<Long>> list;
	static long ans=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		x = sc.nextLong();
		list=new ArrayList<>();
		for (int i=0; i<n; i++) {
			int l = sc.nextInt();
			List<Long> list0=new ArrayList<>();
			for (int j=0; j<l; j++) {
				long a = sc.nextLong();
//				if (x/a*a==x) list0.add(a);
				list0.add(a);
			}
			Collections.sort(list0);
			list.add(list0);
		}
//		System.out.println(list);
		dfs(0, 1);
		System.out.println(ans);
	}
	static void dfs(int i, long val) {
//		System.out.println(i+" "+val);
		if (i==n) {
			if (val==x) ans++;
			return;
		}
		List<Long> list0=list.get(i);
		for (long j : list0) {
			if (j>x/val) continue;
			long y=j*val;
			dfs(i+1, y);
		}
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
