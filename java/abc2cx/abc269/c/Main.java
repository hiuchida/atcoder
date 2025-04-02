import java.util.*;
public class Main {
	static int idx;
	static long[] ary=new long[16];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		init(n);
//		System.out.println(Arrays.toString(ary));
		if (idx==0) {
			System.out.println(0);
			System.exit(0);
		}
		for (int i=0; i< 1<<idx; i++) {
			long x=0;
			for (int j=0; j<idx; j++) {
				int mask=1 << j;
				if ((i&mask)>0) {
					x+=ary[j];
				}
			}
			System.out.println(x);
		}
	}
	static void init(long n) {
		long x=1;
		while (n>0) {
			if ((n&1)>0) {
				ary[idx]=x;
				idx++;
			}
			x*=2;
			n/=2;
		}
	}
}
/*
11

0

576461302059761664
*/
