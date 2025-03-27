import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] aa=new int[n];
		int[] ab=new int[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
		}
		for (int i=0; i<n; i++) {
			ab[i]=sc.nextInt();
		}
		boolean f0=true;
		boolean f1=true;
		for (int i=0; i<n-1; i++) {
			int a0=aa[i];
			int a1=aa[i+1];
			int b0=ab[i];
			int b1=ab[i+1];
			int d00=Math.abs(a0-a1);
			int d01=Math.abs(a0-b1);
			int d10=Math.abs(b0-a1);
			int d11=Math.abs(b0-b1);
			boolean g0=false;
			boolean g1=false;
			if (f0) {
				if (d00<=k) g0=true;
				if (d01<=k) g1=true;
			}
			if (f1) {
				if (d10<=k) g0=true;
				if (d11<=k) g1=true;
			}
//			System.out.println(d00+" "+d01+" "+d10+" "+d11);
//			System.out.println(f0+" "+f1+" "+g0+" "+g1);
			if (!g0 && !g1) {
				System.out.println("No");
				System.exit(0);
			}
			f0=g0;
			f1=g1;
		}
		System.out.println("Yes");
	}
}
/*
5 4
9 8 3 7 2
1 6 2 9 5

4 90
1 1 1 100
1 2 3 100


4 1000000000
1 1 1000000000 1000000000
1 1000000000 1 1000000000
*/
