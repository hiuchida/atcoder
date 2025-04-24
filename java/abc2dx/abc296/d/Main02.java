import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=(int)1e6;
		Prime pr=new Prime(N+1);
		long n=sc.nextLong();
		long m=sc.nextLong();
		if (n<m/n) {
			System.out.println(-1);
			System.exit(0);
		}
		long mm=m;
		MyArray ma=new MyArray();
		for (int v : pr.lst) {
			while (mm%v==0) {
				ma.add(v);
				mm/=v;
			}
		}
//		System.out.println(ma);
		if (mm>n) {
			System.out.println(-1);
			System.exit(0);
		}
		long ans=Long.MAX_VALUE;
		long k=1;
		while (k*k<m) k++;
		for (long a=1; a<=k; a++) {
			if (a>n) break;
			for (long b=m/a-100; b<m/a+100; b++) {
				if (b>n) continue;
				if (a*b>=m) {
//					System.out.println(a+" "+b+" "+(a*b));
					ans=Math.min(ans, a*b);
				}
			}
		}
		if (ans==Long.MAX_VALUE) ans=-1;
		System.out.println(ans);
	}
	static class MyArray { //Prime20250424
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
		String join(String delimiter) {
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<size; i++) {
				if (i>0) sb.append(delimiter);
				sb.append(array[i]);
			}
			return sb.toString();
		}
		@Override
		public String toString() {
			return Arrays.toString(array) + " " + size;
		}
	}
	//1,000,000,000 16678ms
	//  100,000,000  1537ms
	//   10,000,000    98ms
	//    1,000,000    17ms
	static class Prime { //Prime20250424
		int n;
		boolean[] isp;
		int[] minf;
//		List<Integer> list = new ArrayList<>();
		int[] lst;
		Prime(int n) {
			this.n = n;
			this.isp = new boolean[n];
			this.minf = new int[n];
			init();
		}
		void init() {
			MyArray ma=new MyArray();
			isp[0] = false;
			isp[1] = false;
			for (int i=2; i<n; i++) isp[i] = true;
			minf[1] = 1;
			for (int i=2; i<n; i++) {
				if (isp[i]) {
					minf[i] = i;
//					list.add(i);
					ma.add(i);
					for (int j=i*2; j<n; j+=i) {
						isp[j] = false;
						if (minf[j] == 0) minf[j] = i;
					}
				}
			}
			lst=ma.toArray();
		}
		boolean check(long x) {
			if (x>(long)n*n) throw new RuntimeException();
			if (x<n) return isp[(int)x];
//			for (int i : list) {
			for (int i : lst) {
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
		int[] factors(int n) {
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
5 7

2 5

100000 10000000000
*/
/*
10000 10000000000

1000000 100000000007

1000000 1000000000000
*/
