import java.util.*;
public class Main {
	static int MAX=(int)3e6;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		System.out.println(MAX);
		int t = sc.nextInt();
		Prime pr=new Prime(MAX+1);
		for (int tt=0; tt<t; tt++) {
			long n = sc.nextLong();
			for (long p0 : pr.list) {
				long p=0;
				long q=0;
				if (n/p0*p0!=n) continue;
				if (n/p0/p0*p0*p0==n) {
					p=p0;
					q=n/p/p;
					if (p*p*q!=n) continue;
				} else {
					q=p0;
					p=(long)Math.sqrt(n/q);
					if (p*p*q!=n) continue;
				}
				System.out.println(p+" "+q);
				break;
			}
		}
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
3
2023
63
1059872604593911
*/
