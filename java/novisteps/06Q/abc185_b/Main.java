import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int t=sc.nextInt();
		int[] aa=new int[m];
		int[] ab=new int[m];
		for (int i=0; i<m; i++) {
			aa[i]=sc.nextInt();
			ab[i]=sc.nextInt();
		}
		int x=n;
		int st=0;
		for (int i=0; i<m; i++) {
			int a=aa[i];
			int d=aa[i]-st;
//			System.out.println(i+" "+x+" -"+d);
			if (x<=d) ng();
			x-=d;
			x+=ab[i]-aa[i];
			x=Math.min(x, n);
			st=ab[i];
//			System.out.println(i+" "+x);
		}
		int d=t-st;
//		System.out.println("m "+x+" -"+d);
		if (x<=d) ng();
		System.out.println("Yes");
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
}
/*
10 2 20
9 11
13 17

10 2 20
9 11
13 16

15 3 30
5 8
15 17
24 27

20 1 30
20 29

20 1 30
1 10
*/
