import java.util.*;
public class Main {
	static Prime pr;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		pr=new Prime(n+1);
		long ans=0;
		for (int i=1; i<n; i++) {
			int j=n-i;
			long c1=calc(i);
			long c2=calc(j);
			ans+=c1*c2;
//			System.out.println(i+" "+c1+" , "+j+" "+c2);
		}
		System.out.println(ans);
	}
	static long calc(int n) {
		long ans=1;
		while (n>1) {
			for (int nxt : pr.list) {
				if (n==1) break;
				int cnt=1;
				while (n%nxt==0) {
					cnt++;
					n/=nxt;
				}
				ans*=cnt;
			}
		}
		return ans;
	}
	//1,000,000,000 16678ms
	//  100,000,000  1537ms
	//   10,000,000    98ms
	//    1,000,000    17ms
	static class Prime { //Prime20250330
		int n;
		boolean[] isp;
		List<Integer> list = new ArrayList<>();
		Prime(int n) {
			this.n = n;
			this.isp = new boolean[n];
			init();
		}
		void init() {
			isp[0] = false;
			isp[1] = false;
			for (int i=2; i<n; i++) isp[i] = true;
			for (int i=2; i<n; i++) {
				if (isp[i]) {
					list.add(i);
					for (int j=i*2; j<n; j+=i) isp[j] = false;
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
	}
}
/*
4

292

19876
*/
