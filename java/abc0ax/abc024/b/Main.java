import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int t=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		long ans=0;
		int pre=-1;
		for (int i=0; i<n; i++) {
			int a=ary[i];
			if (pre<=a) {
				ans+=t;
//				System.out.println(a+" "+t);
			} else {
				ans+=t-(pre-a);
//				System.out.println(a+" "+(t-(pre-a)));
			}
			pre=a+t;
		}
		System.out.println(ans);
	}
}
/*
5 10
20
100
105
217
314

10 10
1
2
3
4
5
6
7
8
9
10

10 100000
3
31
314
3141
31415
314159
400000
410000
500000
777777
*/
