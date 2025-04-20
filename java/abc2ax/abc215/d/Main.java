import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int M=(int)1e5;
		Prime pr=new Prime(M+1);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[] ary=new int[n];
		int[] ap=new int[M+1];
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			ary[i]=a;
			for (int v : pr.factors(a)) {
				ap[v]++;
			}
		}
//		System.out.println(Arrays.toString(ap));
		List<Integer> list=new ArrayList<>();
		list.add(1);
		for (int i=2; i<=m; i++) {
			if (ap[i]>0) continue;
			boolean bok=true;
			for (int v : pr.factors(i)) {
				if (ap[v]>0) {
					bok=false;
					break;
				}
			}
			if (bok) list.add(i);
		}
		System.out.println(list.size());
		for (int v : list) {
			System.out.println(v);
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
3 12
6 1 5
*/
