import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Prime pr=new Prime(n+1);
		long ans=0;
		for (int i=1; i<n; i++) {
			int j=n-i;
			long c1=pr.count(i);
			long c2=pr.count(j);
			ans+=c1*c2;
//			System.out.println(i+" "+c1+" , "+j+" "+c2);
		}
		System.out.println(ans);
	}
	//1,000,000,000 16678ms
	//  100,000,000  1537ms
	//   10,000,000    98ms
	//    1,000,000    17ms
	static class Prime { //Prime20250418
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
		boolean check(int x) {
			if (x>2*n) throw new RuntimeException();
			if (x<n) return isp[x];
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
	}
}
/*
4

292

19876
*/
