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
			for (int porq : pr.list) {
				if (n/porq*porq!=n) continue;
				if (porq>n/porq) {
					int q=porq;
					long pp=n/q;
					long p=(long)Math.sqrt(pp);
					if (p*p*q!=n) continue;
					System.out.println(p+" "+q);
					break;
				}
				long p=porq;
				long pp=p*p;
				long q=n/pp;
				if (pp*q!=n) continue;
				System.out.println(p+" "+q);
			}
		}
	}
	//1,000,000,000 16678ms
	//  100,000,000  1537ms
	//   10,000,000    98ms
	//    1,000,000    17ms
	static class Prime { //Prime20250103
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
