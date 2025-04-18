import java.util.*;
public class Main {
	static final long M=1000000007; //10^9+7
	static Prime pr;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		pr=new Prime(n+1);
		int[] exp=new int[n+1];
		for (int i=2; i<=n; i++) {
			int[] f1=factors(i);
//			int[][] f2=factorize(i);
//			System.out.println(i+": "+Arrays.toString(f1));
//			for (int j=0; j<f2.length; j++) {
//				System.out.println(i+": "+Arrays.toString(f2[j]));
//			}
			for (int v : f1) {
				exp[v]++;
			}
//			for (int j=0; j<f2.length; j++) {
//				exp[f2[j][0]]+=f2[j][1];
//			}
		}
//		System.out.println(Arrays.toString(exp));
		long ans=1;
		for (int i=2; i<=n; i++) {
			ans=modmul(ans, exp[i]+1);
		}
		System.out.println(ans);
	}
	static int[] factors(int n) {
		List<Integer> list=new ArrayList<>();
		while (n>1) {
			int nxt=pr.minf[n];
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
	static int[][] factorize(int n) {
		List<Integer> lb=new ArrayList<>();
		List<Integer> le=new ArrayList<>();
		while (n>1) {
			int nxt=pr.minf[n];
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
	//abc052_c,abc065_c,abc211_c,abc211_d,abc291_d: valをMで割った余り
	static long mod(long val) {
		return val%M;
	}
	//abc052_c,abc065_c: val*xをMで割った余り
	static long modmul(long val, long x) {
		return mod(val*x);
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
3

6

1000
*/
