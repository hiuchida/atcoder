import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int p = sc.nextInt();
		int q = sc.nextInt();
		int r = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		long[] sum=new long[n+1];
		for (int i=0; i<n; i++) {
			sum[i+1]=sum[i]+ary[i];
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(sum));
		int x=1;
		int y=x+1;
		int z=y+1;
		int w=z+1;
		while (true) {
			y=x+1;
			while (true) {
//				System.out.println(x+" "+y+" "+(sum[y-1]-sum[x-1]));
				if (sum[y-1]-sum[x-1]==p) {
					z=y+1;
					while (true) {
//						System.out.println(x+" "+y+" "+z+" "+(sum[z-1]-sum[y-1]));
						if (sum[z-1]-sum[y-1]==q) {
							w=z+1;
							while (true) {
//								System.out.println(x+" "+y+" "+z+" "+w+" "+(sum[w-1]-sum[z-1]));
								if (sum[w-1]-sum[z-1]==r) {
									break;
								}
								w++;
								if (z>=n) break;
							}
							if (sum[w-1]-sum[z-1]==r) break;
						}
						z++;
						if (z>=n) break;
					}
					if (sum[z-1]-sum[y-1]==q) break;
					if (sum[w-1]-sum[z-1]==r) break;
				}
				y++;
				if (y>=n) break;
			}
			if (sum[y-1]-sum[x-1]==p) break;
			if (sum[z-1]-sum[y-1]==q) break;
			if (sum[w-1]-sum[z-1]==r) break;
			x++;
			if (x>=n) {
				System.out.println("No");
				System.exit(0);
			}
		}
//		System.out.println("ans "+x+" "+y+" "+z+" "+w);
		System.out.println("Yes");
	}
}
/*
10 5 7 5
1 3 2 2 2 3 1 4 3 2

9 100 101 100
31 41 59 26 53 58 97 93 23

7 1 1 1
1 1 1 1 1 1 1
*/
