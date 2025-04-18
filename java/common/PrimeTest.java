import java.util.*;
public class Main {
	//100,000,000 5,061ms 90,756ms
	// 10,000,000   387ms  5,890ms
	//  1,000,000    22ms    664ms
	public static void main(String[] args) {
		final int N=1*1000*1000;
		long st1=System.currentTimeMillis();
		Prime pr=new Prime(N+1);
		long st2=System.currentTimeMillis();
		for (int i=0; i<=N; i++) {
			int[] ap=pr.divisors(i);
			long cnt=pr.count(i);
			if (ap.length!=cnt) {
				System.out.println(i+":"+Arrays.toString(ap));
			}
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of check "+tm2+" "+tm3);
	}
	//400,000,000 (N=20,000) 2ms 23,729ms 416,974ms
	//100,000,000 (N=10,000) 1ms  5,196ms 110,924ms
	//  1,000,000 (N= 1,000) 0ms     22ms     156ms
	//     10,000 (N=   100) 0ms      1ms       3ms
	public static void main_check(String[] args) {
		final int N=100;
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
		System.out.println("end of check "+tm2+" "+tm3+" "+tm4);
	}
	//1,000,000,000 16678ms
	//  100,000,000  1537ms
	//   10,000,000    98ms
	//    1,000,000    17ms
	static class Prime { //Prime20250420
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
				if (x%i==0) {
					return false;
				}
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
		int[] divisors(int n) {
			List<Integer> list=new ArrayList<>();
			list.add(1);
			while (n>1) {
				List<Integer> lst=new ArrayList<>();
				int nxt=minf[n];
				int val=nxt;
				while (n%nxt==0) {
					for (int v : list) {
						lst.add(v*val);
					}
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
	}
}
/*
*/
