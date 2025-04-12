import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int sx=sc.nextInt();
		int sy=sc.nextInt();
		int gx=sc.nextInt();
		int gy=sc.nextInt();
		if (sx>gx) {
			int tx=sx;
			int ty=sy;
			sx=gx;
			sy=gy;
			gx=tx;
			gy=ty;
		}
		sy=-sy;
		double grad=(double)(gy-sy)/(gx-sx);
		double inte=sy-grad*sx;
//		System.out.println(grad);
//		System.out.println(inte);
		double ans=-1*inte/grad;
		System.out.println(ans);
	}
}
/*
1 1 7 2

1 1 3 2

-9 99 -999 9999
*/
