import java.util.*;
public class Main {
	static int N=1 << 20;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long[] ary=new long[N];
		Arrays.fill(ary, -1);
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
			int t = sc.nextInt();
			long x = sc.nextLong();
			int h=(int)(x%N);
			if (t==1) {
				while (ary[h]>=0) h=(h+1)%N;
				ary[h]=x;
			} else {
				System.out.println(ary[h]);
			}
		}
	}
}
/*
4
1 1048577
1 1
2 2097153
2 3
*/
/*
8
1 1048577
1 1048576
1 1048575
1 1048575
2 1048575
2 0
2 1
2 2
*/
