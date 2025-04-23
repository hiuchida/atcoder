import java.util.*;
public class Main {
	public static void main(String[] args) {
		final long M=(long)1e12;
		final int m=(int)1e6;
		Prime pr=new Prime(m+1);
		System.out.println(pr.list.size());
		int cnt=0;
		long st1=System.currentTimeMillis();
		for (long v : pr.list) {
			long k=v*v;
			cnt++;
			if (cnt%1000==0) {
				long st2=System.currentTimeMillis();
				long tm1=(st2-st1)/1000;
				System.out.println("#"+cnt+" "+k+" "+tm1+"s");
			}
			if (k<m) continue;
			long a0=solve0(k);
			long a=solve(k);
			if (a0!=a) {
				System.out.println(k+" "+a0+" "+a);
//				return;
			}
		}
		long st2=System.currentTimeMillis();
		long tm1=(st2-st1)/1000;
		System.out.println("#end"+" "+tm1+"s");
	}
//	public static void main(String[] args) {
	static long solve0(long k) {
		Scanner sc=new Scanner(System.in);
		final int M=(int)1e6;
//		long k=sc.nextLong();
		long p,a,n,x;
		long ans=1;
		for (p=2; p*p<=k; p++) {
			a=0;
			while (k%p==0) {
				k/=p;
				a++;
			}
			n=0;
			while (a>0) {
				n+=p;
				x=n;
				while (x%p==0) {
					x/=p;
					a--;
				}
			}
			ans=Math.max(ans, n);
		}
		ans=Math.max(ans, k);
//		System.out.println(ans);
		return ans;
	}
//	public static void main(String[] args) {
	static long solve(long k) {
		Scanner sc=new Scanner(System.in);
		final int M=(int)1e6;
		Prime pr=new Prime(M+1);
//		for (int i=0; i<=M; i++) {
//			if (pr.minf[i]==0) {
//				System.out.println(i);
//			}
//		}
//		long k=sc.nextLong();
		long cnt=0;
		int[] ary=new int[M+1];
		for (int v : pr.list) {
			while (k%v==0) {
				cnt++;
				ary[v]++;
				k/=v;
//				System.out.println(cnt+" "+v);
			}
		}
		if (cnt==0) {
//			System.out.println(k);
			return k;
//			System.exit(0);
		}
		if (k>1) {
//			System.out.println(k);
			return k;
//			System.exit(0);
		}
		for (int i=2; i<=M; i++) {
			MyArray ma=pr.factors(i);
			for (int j=0; j<ma.size; j++) {
				int v=ma.array[j];
//				System.out.println(v);
				if (ary[v]>0) {
					cnt--;
					ary[v]--;
				}
			}
			if (cnt==0) {
//				System.out.println(i);
				return i;
//				System.exit(0);
			}
		}
		return -1;
	}
	static class MyArray { //MyArray_int20250420
		int[] array;
		int size=0;
		MyArray() {
			this(100);
		}
		MyArray(int n) {
			this.array = new int[n + 1];
		}
		void add(int v) {
			if (array.length == size) array = Arrays.copyOf(array, size * 2);
			array[size++] = v;
		}
		int remove() {
			if (size == 0) return -1;
			return array[--size];
		}
		void trimToSize() {
			if (size < array.length) array = Arrays.copyOf(array, size);
		}
		int[] toArray() {
			int[] ans=new int[size];
			System.arraycopy(array, 0, ans, 0, size);
			return ans;
		}
	}
	//1,000,000,000 16678ms
	//  100,000,000  1537ms
	//   10,000,000    98ms
	//    1,000,000    17ms
	static class Prime { //Prime20250421
		int n;
		boolean[] isp;
		int[] minf;
		List<Integer> list = new ArrayList<>();
		Prime(int n) {
			this.n = n;
			this.isp = new boolean[n];
			this.minf = new int[n];
			init();
		}
		void init() {
			isp[0] = false;
			isp[1] = false;
			for (int i=2; i<n; i++) isp[i] = true;
			minf[1] = 1;
			for (int i=2; i<n; i++) {
				if (isp[i]) {
					minf[i] = i;
					list.add(i);
					for (int j=i*2; j<n; j+=i) {
						isp[j] = false;
						if (minf[j] == 0) minf[j] = i;
					}
				}
			}
		}
		boolean check(long x) {
			if (x>(long)n*n) throw new RuntimeException();
			if (x<n) return isp[(int)x];
			for (int i : list) {
				if (x%i==0) return false;
			}
			return true;
		}
		long count(int n) {
			long ans=1;
			while (n>1) {
				int nxt=minf[n];
				int cnt=1;
				while (n%nxt==0) {
					cnt++;
					n/=nxt;
				}
				ans*=cnt;
			}
			return ans;
		}
		long sum(int n) {
			long ans=1;
			while (n>1) {
				int nxt=minf[n];
				int val=1;
				long sum=1;
				while (n%nxt==0) {
					val*=nxt;
					sum+=val;
					n/=nxt;
				}
				ans*=sum;
			}
			return ans;
		}
		int[] divisors(int n) {
			List<Integer> list=new ArrayList<>();
			list.add(1);
			while (n>1) {
				List<Integer> lst=new ArrayList<>();
				int nxt=minf[n];
				int val=nxt;
				while (n%nxt==0) {
					for (int v : list) lst.add(v*val);
					n/=nxt;
					val*=nxt;
				}
				list.addAll(lst);
			}
			Collections.sort(list);
			int[] ans=new int[list.size()];
			for (int i=0; i<list.size(); i++) {
				ans[i]=list.get(i);
			}
			return ans;
		}
		int[] factors0(int n) {
			List<Integer> list=new ArrayList<>();
			while (n>1) {
				int nxt=minf[n];
				while (n%nxt==0) {
					list.add(nxt);
					n/=nxt;
				}
			}
			int[] ans=new int[list.size()];
			for (int i=0; i<list.size(); i++) {
				ans[i]=list.get(i);
			}
			return ans;
		}
		MyArray factors(int n) {
			MyArray ma=new MyArray();
//			List<Integer> list=new ArrayList<>();
			while (n>1) {
				int nxt=minf[n];
				while (n%nxt==0) {
					ma.add(nxt);
//					list.add(nxt);
					n/=nxt;
				}
			}
			return ma;
//			return list;
		}
		int[][] factorize(int n) {
			List<Integer> lb=new ArrayList<>();
			List<Integer> le=new ArrayList<>();
			while (n>1) {
				int nxt=minf[n];
				int cnt=0;
				while (n%nxt==0) {
					n/=nxt;
					cnt++;
				}
				lb.add(nxt);
				le.add(cnt);
			}
			int[][] ans=new int[lb.size()][2];
			for (int i=0; i<lb.size(); i++) {
				ans[i][0]=lb.get(i);
				ans[i][1]=le.get(i);
			}
			return ans;
		}
	}
}
/*
30

123456789011

280
*/
/*
999997

999998

999999

1000000

1000001

1000002

1000003

2999954

899627589169
*/
