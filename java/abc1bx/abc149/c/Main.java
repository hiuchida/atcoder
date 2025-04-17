import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Prime pr=new Prime(n+1);
		if (pr.isp[n]) {
			System.out.println(n);
			System.exit(0);
		}
		for (int i=n+1; true; i++) {
			boolean bhit=false;
			for (int j : pr.list) {
				if (i%j==0) {
					bhit=true;
					break;
				}
			}
			if (!bhit) {
				System.out.println(i);
				System.exit(0);
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
20

2

99992
*/
