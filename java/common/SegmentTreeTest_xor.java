import java.util.*;
public class Main {
	public static void main(String[] args) {
//		main0(args);
		main1(1);
		main1(10);
		main1(100);
	}
	public static void main0(String[] args) {
		int n = 14;
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
//			int v=(i * 3) % n + 1;
			int v=i+1;
			ary[i]=v;
		}
		System.out.println(Arrays.toString(ary));
		SegmentTree st = new SegmentTree(n, 0, 0);
		for (int i=0; i<n; i++) {
			st.update(i, ary[i]);
		}
		System.out.println(st);
		for (int j=0; j<n; j++) {
			for (int i=j; i<=n; i++) {
				int v=st.query(j, i);
				System.out.println(j+" "+i+" "+v);
			}
		}
	}
	public static void main1(int n) {
		final int N=n*1024*1024;
		final int C=1024*1024*1024;
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
		int[] ans1s=main1Segment(N, C, query, seed);
		for (int i=0; i<ans1a.length; i++) {
			if (ans1a[i]!=ans1s[i]) {
				System.out.println(i+" "+ans1a[i]+" "+ans1s[i]);
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
			int xor=0;
			for (int j=query[i][0]; j<query[i][1]; j++) {
				xor=xor ^ ary[j];
			}
			ans[i]=xor;
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of main1Array "+tm2+" "+tm3);
		return ans;
	}
	public static int[] main1Segment(final int N, final int C, int[][] query, long seed) {
		Random r=new Random(seed);
		long st1=System.currentTimeMillis();
		int[] ary=new int[N];
		for (int i=0; i<N; i++) {
			ary[i]=r.nextInt(C);
		}
		SegmentTree st=new SegmentTree(N, 0, 0);
		for (int i=0; i<N; i++) {
			st.update(i, ary[i]);
		}
		long st2=System.currentTimeMillis();
		int[] ans=new int[query.length];
		for (int i=0; i<query.length; i++) {
			ans[i]=st.query(query[i][0], query[i][1]);
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of main1Segment "+tm2+" "+tm3);
		return ans;
	}
	static class SegmentTree { //SegmentTree_xor20250427
		int siz;
		int[] ary;
		int def;
		int inf;
		SegmentTree(int n, int def, int inf) {
			this.siz = ceil2pow(n);
			this.ary = new int[2 * siz];
			this.def = def;
			this.inf = inf;
			Arrays.fill(ary, def);
		}
		int xor(int a, int b) {
			return a ^ b;
		}
		void update(int i, int value) {
			i += siz - 1;
			ary[i] = xor(ary[i], value);
			while (i > 0) {
				i = (i - 1) / 2;
				ary[i] = xor(ary[2 * i + 1], ary[2 * i + 2]);
			}
		}
		int query(int a, int b) {
			return query(a, b, 0, 0, siz);
		}
		int query(int a, int b, int k, int lt, int rt) {
			if (rt <= a || b <= lt) {
//				System.out.println(a+" "+b+" "+k+" "+lt+" "+rt+" -> "+def);
				return inf;
			}
			if (a <= lt && rt <= b) {
//				System.out.println(a+" "+b+" "+k+" "+lt+" "+rt+" -> "+ary[k]);
				return ary[k];
			}
//			System.out.println(a+" "+b+" "+k+" "+lt+" "+rt);
			int vl = query(a, b, 2 * k + 1, lt, (lt + rt) / 2);
			int vr = query(a, b, 2 * k + 2, (lt + rt) / 2, rt);
			return xor(vl, vr);
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<ary.length; i++) {
				String v = ary[i] != inf ? "" + ary[i] : "-";
				sb.append(v + " ");
			}
			return sb.toString();
		}
		private int ceil2pow(int n) {
			if (n == 0) return 1;
			n--;
			n |= (n >>> 1);
			n |= (n >>> 2);
			n |= (n >>> 4);
			n |= (n >>> 8);
			n |= (n >>> 16);
			n++;
			return n;
			/*
			int pow = 1;
			while (pow < n) pow <<= 1;
			return pow;
			*/
		}
	}
}
/*
end of main1Array 13 98
end of main1Segment 63 1
end of main1Array 104 1750
end of main1Segment 561 0
end of main1Array 972 16566
end of main1Segment 6118 1
*/
