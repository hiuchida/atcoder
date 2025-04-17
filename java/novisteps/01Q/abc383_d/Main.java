import java.util.*;
public class Main {
	static void DEBUG(Object x) {
//		System.out.println(x);
	}
	static void DEBUG(long x) {
		DEBUG(""+x);
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
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		Prime p = new Prime(2*1000*1000+1);
		DEBUG(p.list);
		long ans = 0;
		for (long i : p.list) {
			if (i*i*i*i>n) break;
			if (i<100) {
				long k=i*i*i*i;
				if (k*k<=n) ans++;
			}
			for (long j : p.list) {
				if (j<=i) continue;
				if (i*i*j*j<=n) ans++;
				else break;
			}
		}
		System.out.println(ans);
	}
}
