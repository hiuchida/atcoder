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
			int v=(i * 3) % n + 1;
//			int v=i+1;
			ary[i]=v;
		}
		System.out.println(Arrays.toString(ary));
		SegmentTree st = new SegmentTree(n, -1, Integer.MIN_VALUE);
		for (int i=0; i<n; i++) {
			st.update(i, ary[i]);
		}
		System.out.println(st);
//		for (int j=0; j<n; j++) {
//			for (int i=j; i<=n; i++) {
//				int v=st.query(j, i);
//				System.out.println(j+" "+i+" "+v);
//			}
//		}
		for (int i=n; i>0; i--) {
			for (int j=0; j<i; j++) {
				int x=3;
				int idx=st.findLeft(j, i, x);
				int v=st.query(idx, idx + 1);
				System.out.println(j+" "+i+" "+x+" -> "+idx+"="+v);
			}
		}
//		for (int j=0; j<n; j++) {
//			for (int i=n; i>j; i--) {
//				int x=3;
//				int idx=st.findRight(j, i, x);
//				int v=st.query(idx, idx + 1);
//				System.out.println(j+" "+i+" "+x+" -> "+idx+"="+v);
//			}
//		}
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
			int max=0;
			for (int j=query[i][0]; j<query[i][1]; j++) {
				max=Math.max(max, ary[j]);
			}
			ans[i]=max;
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
		SegmentTree st=new SegmentTree(N, 0, Integer.MIN_VALUE);
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
	static class SegmentTree { //SegmentTree_max20250429
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
		int max(int a, int b) {
			return Math.max(a, b);
		}
		void update(int i, int value) {
			i += siz - 1;
			ary[i] = value;
			while (i > 0) {
				i = (i - 1) / 2;
				ary[i] = max(ary[2 * i + 1], ary[2 * i + 2]);
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
			return max(vl, vr);
		}
		int findLeft(int a, int b, int x) {
			return findLeft(a, b, x, 0, 0, siz);
		}
		int findRight(int a, int b, int x) {
			return findRight(a, b, x, 0, 0, siz);
		}
		int findLeft(int a, int b, int x, int k, int lt, int rt) {
			if (ary[k] < x || rt <= a || b <= lt) {
				return b;
			}
			if (k >= siz - 1) {
				return k - (siz - 1);
			}
			int vl = findLeft(a, b, x, 2 * k + 1, lt, (lt + rt) / 2);
			if (vl != b) {
				return vl;
			}
			return findLeft(a, b, x, 2 * k + 2, (lt + rt) / 2, rt);
		}
		int findRight(int a, int b, int x, int k, int lt, int rt) {
			if (ary[k] < x || rt <= a || b <= lt) {
				return a - 1;
			}
			if (k >= siz - 1) {
				return k - (siz - 1);
			}
			int vr = findRight(a, b, x, 2 * k + 2, (lt + rt) / 2, rt);
			if (vr != a- 1) {
				return vr;
			}
			return findRight(a, b, x, 2 * k + 1, lt, (lt + rt) / 2);
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
end of main1Array 14 187
end of main1Segment 61 1
end of main1Array 104 1991
end of main1Segment 601 1
end of main1Array 981 20142
end of main1Segment 6372 1
*/
