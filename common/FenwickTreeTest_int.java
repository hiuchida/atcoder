import java.util.*;
public class Main {
	public static void main(String[] args) {
//		main0(args);
		main1(1);
		main1(10);
		main1(100);
	}
	public static void main0(String[] args) {
		int n = 10;
		int[] ary0=new int[n];
		for (int i=0; i<n; i++) {
//			int v=(i * 3) % n + 1;
			int v=i+1;
			ary0[i]=v;
		}
		int[] ary=Arrays.copyOf(ary0, ary0.length);
		System.out.println(Arrays.toString(ary));
		FenwickTree ft = new FenwickTree(ary);
		System.out.println(Arrays.toString(ary));
		for (int i=0; i<n; i++) {
			int v=ft.get(i);
			if (v!=ary0[i]) {
				System.out.println(i+" "+v+" "+ary0[i]);
			}
		}
		for (int j=0; j<10; j++) {
			for (int i=1; i<=3; i++) {
				ft.add(j, 1);
				System.out.println(Arrays.toString(ary)+" "+j+" "+i);
			}
			ary0[j]+=3;
			for (int i=0; i<n; i++) {
				int v=ft.get(i);
				if (v!=ary0[i]) {
					System.out.println(j+" "+i+" "+v+" "+ary0[i]);
				}
			}
		}
	}
	public static void main1(int n) {
		final int N=n*1024*1024;
		final int C=2048/n;
		final int Q=1000;
		Random r=new Random();
		int[][] query=new int[Q][2];
		for (int i=0; i<Q; i++) {
			while (true) {
				int p=r.nextInt(N);
				int q=r.nextInt(N);
				if (p<q) {
					query[i][0]=p;
					query[i][1]=q;
					break;
				}
			}
		}
		long seed=System.currentTimeMillis();
		int[] ans1a=main1Array(N, C, query, seed);
		int[] ans1f=main1Fenwick(N, C, query, seed);
		for (int i=0; i<ans1a.length; i++) {
			if (ans1a[i]!=ans1f[i]) {
				System.out.println(i+" "+ans1a[i]+" "+ans1f[i]);
			}
		}
	}
	public static int[] main1Array(final int N, final int C, int[][] query, long seed) {
		Random r=new Random(seed);
		long st1=System.currentTimeMillis();
		int[] ary=new int[N];
		for (int i=0; i<N; i++) {
			ary[i]=r.nextInt(C);
		}
		long st2=System.currentTimeMillis();
		int[] ans=new int[query.length];
		for (int i=0; i<query.length; i++) {
			int sum=0;
			for (int j=query[i][0]; j<query[i][1]; j++) {
				sum+=ary[j];
			}
			ans[i]=sum;
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of main1Array "+tm2+" "+tm3);
		return ans;
	}
	public static int[] main1Fenwick(final int N, final int C, int[][] query, long seed) {
		Random r=new Random(seed);
		long st1=System.currentTimeMillis();
		int[] ary=new int[N];
		for (int i=0; i<N; i++) {
			ary[i]=r.nextInt(C);
		}
		FenwickTree ft=new FenwickTree(ary);
		long st2=System.currentTimeMillis();
		int[] ans=new int[query.length];
		for (int i=0; i<query.length; i++) {
			ans[i]=ft.range(query[i][0], query[i][1]);
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of main1Fenwick "+tm2+" "+tm3);
		return ans;
	}
	static class FenwickTree { //FenwickTree_int20250427
		int[] ary;
		int size;
		FenwickTree(int size) {
			this.ary = new int[size];
			this.size = size;
		}
		FenwickTree(int[] ary) {
			this.ary = ary;
			this.size = ary.length;
			init();
		}
		private void init() {
			for (int i = 0; i < size; i++) {
				int j = i + lsb(i + 1);
				if (j < size) ary[j] += ary[i];
			}
		}
		void add(int i, int delta) {
			while (i < size) {
				ary[i] += delta;
				i += lsb(i+1);
			}
		}
		void set(int i, int value) {
			add(i, value - get(i));
		}
		int get(int i) {
			return range(i, i + 1);
		}
		int sum(int i) {
			int ans = 0;
			while (i > 0) {
				ans += ary[i - 1];
				i -= lsb(i);
			}
			return ans;
		}
		int range(int i, int j) {
			int ans = 0;
			while (j > i) {
				ans += ary[j - 1];
				j -= lsb(j);
			}
			while (i > j) {
				ans -= ary[i - 1];
				i -= lsb(i);
			}
			return ans;
		}
		private int lsb(int i) {
			return i & (-i);
		}
	}
}
/*
end of main1Array 13 104
end of main1Fenwick 19 1
end of main1Array 106 1708
end of main1Fenwick 121 0
end of main1Array 985 15915
end of main1Fenwick 1031 0
*/
