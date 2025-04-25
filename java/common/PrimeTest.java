import java.util.*;
public class Main {
	public static void main(String[] args) {
		main_init(args);
		main_check(args);
		main_divisors(args);
		main_sum(args);
		main_factorize(args);
		main_factors(args);
	}
	//1,000,000,000  ----ms
	//  100,000,000  4759ms
	//   10,000,000   311ms
	//    1,000,000    20ms
	public static void main_init(String[] args) {
		final int N=1*1000*1000;
		long st1=System.currentTimeMillis();
		Prime pr=new Prime(N+1);
		long st2=System.currentTimeMillis();
		long tm2=st2-st1;
		System.out.println("end of main_init "+tm2);
	}
	//400,000,000 (N=20,000) 2ms 23,729ms 416,974ms
	//100,000,000 (N=10,000) 1ms  5,196ms 110,924ms
	//  1,000,000 (N= 1,000) 0ms     22ms     156ms
	//     10,000 (N=   100) 0ms      1ms       3ms
	public static void main_check(String[] args) {
		final int N=1000;
		long st1=System.currentTimeMillis();
		Prime pr1=new Prime(N+1);
		long st2=System.currentTimeMillis();
		Prime pr2=new Prime(N*N+1);
		long st3=System.currentTimeMillis();
		for (int i=0; i<=N*N; i++) {
			if (pr1.check(i)!=pr2.isp[i]) {
				System.out.println(i);
			}
		}
		long st4=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		long tm4=st4-st3;
		System.out.println("end of main_check "+tm2+" "+tm3+" "+tm4);
	}
	//100,000,000 5,010ms 31,921ms
	// 10,000,000   321ms  2,696ms
	//  1,000,000    10ms    323ms
	public static void main_divisors(String[] args) {
		final int N=1*1000*1000;
		long st1=System.currentTimeMillis();
		Prime pr=new Prime(N+1);
		long st2=System.currentTimeMillis();
		for (int i=0; i<=N; i++) {
			int[] ap=pr.divisors(i);
			long cnt=pr.count(i);
			if (ap.length!=cnt) {
				System.out.println(i+":"+Arrays.toString(ap)+" "+cnt);
			}
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of main_divisors "+tm2+" "+tm3);
	}
	//100,000,000 4,177ms 74,595ms
	// 10,000,000   387ms  6,160ms
	//  1,000,000    23ms    704ms
	public static void main_sum(String[] args) {
		final int N=1*1000*1000;
		long st1=System.currentTimeMillis();
		Prime pr=new Prime(N+1);
		long st2=System.currentTimeMillis();
		for (int i=0; i<=N; i++) {
			long sum=pr.sum(i);
			int[] ap=pr.divisors(i);
			long sum2=0;
			for (int v : ap) sum2+=v;
			if (sum!=sum2) {
				System.out.println(i+":"+Arrays.toString(ap)+" "+sum);
			}
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of main_sum "+tm2+" "+tm3);
	}
	//100,000,000 5,239ms 122,426ms
	// 10,000,000   380ms  10,529ms
	//  1,000,000     7ms   1,028ms
	public static void main_factorize(String[] args) {
		final int N=1*1000*1000;
		long st1=System.currentTimeMillis();
		Prime pr=new Prime(N+1);
		long st2=System.currentTimeMillis();
		for (int i=0; i<=N; i++) {
			int[][] ary=pr.factorize(i);
			int[] ap=pr.divisors(i);
			List<Integer> list=new ArrayList<>();
			list.add(1);
			for (int j=0; j<ary.length; j++) {
				List<Integer> lst=new ArrayList<>();
				int nxt=ary[j][0];
				int val=nxt;
				for (int k=0; k<ary[j][1]; k++) {
					for (int v : list) lst.add(v*val);
					val*=nxt;
				}
				list.addAll(lst);
			}
			Collections.sort(list);
			for (int j=0; j<list.size(); j++) {
				if (list.get(j)!=ap[j]) {
					System.out.println(i+":"+list);
				}
			}
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of main_factorize "+tm2+" "+tm3);
	}
	//100,000,000 4,831ms 37,219ms
	// 10,000,000   345ms  3,223ms
	//  1,000,000     8ms    333ms
	public static void main_factors(String[] args) {
		final int N=1*1000*1000;
		long st1=System.currentTimeMillis();
		Prime pr=new Prime(N+1);
		long st2=System.currentTimeMillis();
		for (int i=0; i<=N; i++) {
			int[] af=pr.factors(i);
			int[][] ary=pr.factorize(i);
			int idx=0;
			for (int j=0; j<ary.length; j++) {
				int v=ary[j][0];
				for (int k=0; k<ary[j][1]; k++) {
					if (af[idx++]!=v) {
						System.out.println(i+" "+j+" "+k);
					}
				}
			}
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of main_factors "+tm2+" "+tm3);
	}
	static class MyArray { //Prime20250426
		int[] array;
		int size=0;
		MyArray() {
			this(100);
		}
		MyArray(int n) {
			this.array = new int[n + 1];
		}
		int size() {
			return size;
		}
		int get(int idx) {
			return array[idx];
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
	//1,000,000,000  ----ms
	//  100,000,000  4759ms
	//   10,000,000   311ms
	//    1,000,000    20ms
	static class Prime { //Prime20250426
		int n;
		boolean[] isp;
		int[] minf;
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
			MyArray ma=new MyArray();
			ma.add(1);
			while (n>1) {
				int[] ary=ma.toArray();
				int nxt=minf[n];
				int val=nxt;
				while (n%nxt==0) {
					for (int v : ary) ma.add(v*val);
					n/=nxt;
					val*=nxt;
				}
			}
			int[] ans=ma.toArray();
			Arrays.sort(ans);
			return ans;
		}
		int[] factors(int n) {
			MyArray ma=new MyArray();
			while (n>1) {
				int nxt=minf[n];
				while (n%nxt==0) {
					ma.add(nxt);
					n/=nxt;
				}
			}
			int[] ans=ma.toArray();
			return ans;
		}
		int[][] factorize(int n) {
			MyArray mab=new MyArray();
			MyArray mae=new MyArray();
			while (n>1) {
				int nxt=minf[n];
				int cnt=0;
				while (n%nxt==0) {
					n/=nxt;
					cnt++;
				}
				mab.add(nxt);
				mae.add(cnt);
			}
			int[][] ans=new int[mab.size()][2];
			for (int i=0; i<mab.size(); i++) {
				ans[i][0]=mab.get(i);
				ans[i][1]=mae.get(i);
			}
			return ans;
		}
	}
}
/*
*/
