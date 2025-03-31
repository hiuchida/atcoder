import java.util.*;
public class Main {
	static int N=1 << 20;
	static long[] ary=new long[N];
	static int[] right=new int[N];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Arrays.fill(ary, -1);
		for (int i=0; i<N; i++) right[i]=i;
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
			int t = sc.nextInt();
			long x = sc.nextLong();
			int h=(int)(x%N);
			if (t==1) {
				set(h, x);
//				while (ary[h]>=0) h=(h+1)%N;
//				ary[h]=x;
			} else {
				System.out.println(ary[h]);
			}
		}
	}
	static int search(int h) {
		if (right[h]==h) return h;
		int hh=right[h];
		int x=search(hh);
		right[h]=x;
		return x;
	}
	static void set(int h, long x) {
		int hh=search(h);
		ary[hh]=x;
		int h2=(hh+1)%N;
		right[hh]=right[h2];
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
