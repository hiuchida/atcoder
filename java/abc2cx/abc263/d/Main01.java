import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int l = sc.nextInt();
		int r = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		long[] sl=new long[n+1];
		for (int i=0; i<n; i++) {
			sl[i+1]=sl[i]+ary[i];
		}
		long[] sr=new long[n+1];
		for (int i=n-1; i>=0; i--) {
			sr[i]=sr[i+1]+ary[i];
		}
		System.out.println("sl: "+Arrays.toString(sl));
		System.out.println("sr: "+Arrays.toString(sr));
		for (int i=1; i<=n; i++) {
			if (sl[i]>=i*l) {
				sl[i]=i*l;
			} else break;
		}
		for (int i=1; i<=n; i++) {
			if (sr[n-i]>=i*r) {
				sr[n-i]=i*r;
			} else break;
		}
		System.out.println("sl: "+Arrays.toString(sl));
		System.out.println("sr: "+Arrays.toString(sr));
		System.out.println();
	}
}
/*
5 4 3
5 5 0 6 3

4 10 10
1 2 3 4

10 -5 -3
9 -6 10 -1 2 10 -1 7 -15 5
*/
