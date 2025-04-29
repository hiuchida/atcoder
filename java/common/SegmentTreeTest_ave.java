import java.util.*;
public class Main {
	public static void main(String[] args) {
//		main0(args);
		main1(1);
		main1(10);
		main1(50);
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
				Bean v=st.query(j, i);
				System.out.println(j+" "+i+" "+v);
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
		Bean[] ans1a=main1Array(N, C, query, seed);
		Bean[] ans1s=main1Segment(N, C, query, seed);
		for (int i=0; i<ans1a.length; i++) {
			if (!ans1a[i].equals(ans1s[i])) {
				System.out.println(i+" "+ans1a[i]+" "+ans1s[i]);
			}
		}
	}
	public static Bean[] main1Array(final int N, final int C, int[][] query, long seed) {
		Random r=new Random(seed);
		long st1=System.currentTimeMillis();
		int[] ary=new int[N];
		for (int i=0; i<N; i++) {
			ary[i]=r.nextInt(C);
		}
		long st2=System.currentTimeMillis();
		Bean[] ans=new Bean[query.length];
		for (int i=0; i<query.length; i++) {
			int cnt=0;
			int sum=0;
			for (int j=query[i][0]; j<query[i][1]; j++) {
				cnt++;
				sum=sum + ary[j];
			}
			ans[i]=new Bean(cnt, sum);
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of main1Array "+tm2+" "+tm3);
		return ans;
	}
	public static Bean[] main1Segment(final int N, final int C, int[][] query, long seed) {
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
		Bean[] ans=new Bean[query.length];
		for (int i=0; i<query.length; i++) {
			ans[i]=st.query(query[i][0], query[i][1]);
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of main1Segment "+tm2+" "+tm3);
		return ans;
	}
	static class Bean { //SegmentTree_ave20250429
		int cnt;
		int sum;
		Bean() {
			this(0, 0);
		}
		Bean(int value) {
			this(1, value);
		}
		Bean(int count, int value) {
			this.cnt=count;
			this.sum=value;
		}
		void set(int value) {
			this.cnt=1;
			this.sum=value;
		}
		@Override
		public int hashCode() {
			return Objects.hash(cnt, sum);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			Bean other = (Bean) obj;
			return cnt == other.cnt && sum == other.sum;
		}
		@Override
		public String toString() {
			return "("+cnt+","+sum+")";
		}
	}
	static class SegmentTree { //SegmentTree_ave20250429
		int siz;
		Bean[] ary;
		int def;
		Bean inf;
		SegmentTree(int n, int def, int inf) {
			this.siz = ceil2pow(n);
			this.ary = new Bean[2 * siz];
			this.def = def;
			this.inf = new Bean(0, inf);
			for (int i=0; i<ary.length; i++) {
				ary[i]=new Bean(0, def);
			}
		}
		Bean add(Bean x, Bean a, Bean b) {
			x.cnt=a.cnt+b.cnt;
			x.sum=a.sum+b.sum;
			return x;
		}
		void update(int i, int value) {
			i += siz - 1;
			ary[i].set(value);
			while (i > 0) {
				i = (i - 1) / 2;
				add(ary[i], ary[2 * i + 1], ary[2 * i + 2]);
			}
		}
		Bean query(int a, int b) {
			return query(a, b, 0, 0, siz);
		}
		Bean query(int a, int b, int k, int lt, int rt) {
			if (rt <= a || b <= lt) {
//				System.out.println(a+" "+b+" "+k+" "+lt+" "+rt+" -> "+def);
				return inf;
			}
			if (a <= lt && rt <= b) {
//				System.out.println(a+" "+b+" "+k+" "+lt+" "+rt+" -> "+ary[k]);
				return ary[k];
			}
//			System.out.println(a+" "+b+" "+k+" "+lt+" "+rt);
			Bean vl = query(a, b, 2 * k + 1, lt, (lt + rt) / 2);
			Bean vr = query(a, b, 2 * k + 2, (lt + rt) / 2, rt);
			return add(new Bean(), vl, vr);
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<ary.length; i++) {
				String v = !ary[i].equals(inf) ? "" + ary[i] : "-";
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
end of main1Array 14 408
end of main1Segment 129 2
end of main1Array 107 4587
end of main1Segment 1429 2
end of main1Array 492 21598
end of main1Segment 5998 2
*/
