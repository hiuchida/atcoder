import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int t=sc.nextInt();
		int[] ac=new int[n];
		for (int i=0; i<n; i++) {
			ac[i]=sc.nextInt();
		}
		int[] ar=new int[n];
		for (int i=0; i<n; i++) {
			ar[i]=sc.nextInt();
		}
		int c1=ac[0];
		int v1=0;
		int i1=0;
		int v2=0;
		int i2=0;
		for (int i=0; i<n; i++) {
			int c=ac[i];
			int r=ar[i];
			if (c==t) {
				if (r>v1) {
					v1=r;
					i1=i+1;
//					System.out.println(i1+" "+v1);
				}
			} else if (c==c1) {
				if (r>v2) {
					v2=r;
					i2=i+1;
//					System.out.println(i2+" "+v2+" "+c);
				}
			}
		}
		if (i1>0) System.out.println(i1);
		else System.out.println(i2);
	}
}
/*
4 2
1 2 1 2
6 3 4 5

4 2
1 3 1 4
6 3 4 5

2 1000000000
1000000000 1
1 1000000000
*/
