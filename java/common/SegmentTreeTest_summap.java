import java.util.*;
public class Main {
	public static void main(String[] args) {
		main0(100);
//		main1(1);
//		main1(10);
//		main1(20);
	}
	public static void main0(int k) {
		int n = 14;
		int[] ary=new int[k*n];
		for (int i=0; i<n; i++) {
//			int v=(i * 3) % n + 1;
			int v=i+1;
			ary[k*i]=v;
		}
		System.out.println(Arrays.toString(ary));
		SegmentTree st = new SegmentTree(k*n, 0, 0);
		for (int i=0; i<n; i++) {
			st.update(k*i, ary[k*i]);
		}
		for (int i=0; i<n; i++) {
			int v=st.get(k*i);
			if (v!=ary[k*i]) {
				System.out.println(k*i+" "+v+" "+ary[k*i]);
			}
			v=st.get(k*i-1);
			if (v!=0) {
				System.out.println(k*i-1+" "+v+" 0");
			}
		}
		System.out.println(st);
		for (int j=0; j<n; j++) {
			for (int i=j; i<=n; i++) {
				int v=st.query(k*j, k*i);
				int sum=0;
				for (int a=k*j; a<k*i; a++) sum+=ary[a];
				if (v!=sum) {
					System.out.println(k*j+" "+k*i+" "+v+" "+sum);
				}
			}
		}
		System.out.println("end of main0");
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
			int sum=0;
			for (int j=query[i][0]; j<query[i][1]; j++) {
				sum=sum + ary[j];
			}
			ans[i]=sum;
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
	static class SegmentTree { //SegmentTree_summap20250502
		int siz;
		Map<Integer, Integer> map=new TreeMap<>();
		int def;
		int inf;
		SegmentTree(int n, int def, int inf) {
			this.siz = ceil2pow(n);
			this.def = def;
			this.inf = inf;
		}
		int add(int a, int b) {
			return a+b;
		}
		void update(int i, int value) {
			i += siz - 1;
			map.put(i, value);
			while (i > 0) {
				i = (i - 1) / 2;
				Integer v1=map.get(2 * i + 1);
				Integer v2=map.get(2 * i + 2);
				if (v1==null && v2==null) map.remove(i);
				else {
					if (v1==null) v1=def;
					if (v2==null) v2=def;
					map.put(i, add(v1, v2));
				}
			}
		}
		int get(int i) {
			i += siz - 1;
			Integer v=map.get(i);
			if (v==null) v=def;
			return v;
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
				Integer v=map.get(k);
				if (v==null) v=0;
				return v;
			}
//			System.out.println(a+" "+b+" "+k+" "+lt+" "+rt);
			int vl = query(a, b, 2 * k + 1, lt, (lt + rt) / 2);
			int vr = query(a, b, 2 * k + 2, (lt + rt) / 2, rt);
			return add(vl, vr);
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
//			for (int i=0; i<ary.length; i++) {
//				String v = ary[i] != inf ? "" + ary[i] : "-";
//				sb.append(v + " ");
//			}
			sb.append(map);
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
{0=105, 1=66, 2=39, 3=21, 4=45, 5=39, 7=6, 8=15, 9=15, 10=30, 11=25, 12=14, 15=3, 16=3, 17=4, 18=11, 19=7, 20=8, 21=9, 22=21, 23=12, 24=13, 25=14, 31=1, 32=2, 34=3, 35=4, 37=5, 38=6, 40=7, 41=8, 43=9, 45=10, 46=11, 48=12, 49=13, 51=14, 63=1, 66=2, 69=3, 72=4, 75=5, 78=6, 81=7, 84=8, 88=9, 91=10, 94=11, 97=12, 100=13, 103=14, 127=1, 133=2, 139=3, 145=4, 152=5, 158=6, 164=7, 170=8, 177=9, 183=10, 189=11, 195=12, 202=13, 208=14, 255=1, 267=2, 280=3, 292=4, 305=5, 317=6, 330=7, 342=8, 355=9, 367=10, 380=11, 392=12, 405=13, 417=14, 511=1, 536=2, 561=3, 586=4, 611=5, 636=6, 661=7, 686=8, 711=9, 736=10, 761=11, 786=12, 811=13, 836=14, 1023=1, 1073=2, 1123=3, 1173=4, 1223=5, 1273=6, 1323=7, 1373=8, 1423=9, 1473=10, 1523=11, 1573=12, 1623=13, 1673=14, 2047=1, 2147=2, 2247=3, 2347=4, 2447=5, 2547=6, 2647=7, 2747=8, 2847=9, 2947=10, 3047=11, 3147=12, 3247=13, 3347=14}

end of main1Array 16 99
end of main1Segment 5436 8
end of main1Array 112 1567
end of main1Segment 96040 13
end of main1Array 314 3665
end of main1Segment 228740 12
*/
