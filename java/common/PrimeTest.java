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
	//100,000,000 5,061ms 90,756ms
	// 10,000,000   387ms  5,890ms
	//  1,000,000    22ms    664ms
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
	//100,000,000 4,861ms 210,781ms
	// 10,000,000   399ms  13,899ms
	//  1,000,000    23ms   1,448ms
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
	//100,000,000 5,184ms 40,773ms
	// 10,000,000   394ms  3,602ms
	//  1,000,000    24ms    417ms
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
	static class MyArray { //Prime20250425
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
	//1,000,000,000  ----ms
	//  100,000,000  4759ms
	//   10,000,000   311ms
	//    1,000,000    20ms
	static class Prime { //Prime20250425
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
*/
