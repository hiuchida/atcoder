import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		int max=(int)1e6;
		Prime pr=new Prime(max);
		long ans=0;
		for (int i=0; i<pr.list.size(); i++) {
			int p=pr.list.get(i);
			if (p>n) break;
			for (int j=i+1; j<pr.list.size(); j++) {
				long q=pr.list.get(j);
				long k=p*q;
				if (k>n/q) break;
				k*=q;
				if (k>n/q) break;
				k*=q;
				if (k>n) break;
//				System.out.println(k);
				ans++;
			}
		}
		System.out.println(ans);
	}
	static class Prime {
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
	}
}
/*
250

1

123456789012345
*/
