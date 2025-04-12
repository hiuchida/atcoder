import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		int max=0;
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			ary[i]=a;
			max=Math.max(max, a);
		}
		int ans=0;
		int gcd=0;
		for (int k=2; k<=max; k++) {
			int c=0;
			for (int i=0; i<n; i++) {
				if (ary[i]%k==0) c++;
			}
			if (gcd<c) {
				gcd=c;
				ans=k;
			}
		}
//		System.out.println(gcd);
		System.out.println(ans);
	}
}
/*
3
3 12 7

5
8 9 18 90 72

5
1000 1000 1000 1000 1000
*/
