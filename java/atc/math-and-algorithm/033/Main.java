import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int ax=sc.nextInt();
		int ay=sc.nextInt();
		int bx=sc.nextInt();
		int by=sc.nextInt();
		int cx=sc.nextInt();
		int cy=sc.nextInt();
		int bax=ax-bx;
		int bay=ay-by;
		int bcx=cx-bx;
		int bcy=cy-by;
		long dotBaBc=calc_dot(bax, bay, bcx, bcy);
//		System.out.println(bax+" "+bay+"  "+bcx+" "+bcy+"  "+dotBaBc);
		
		int cax=ax-cx;
		int cay=ay-cy;
		int cbx=bx-cx;
		int cby=by-cy;
		long dotCaCb=calc_dot(cax, cay, cbx, cby);
//		System.out.println(cax+" "+cay+"  "+cbx+" "+cby+"  "+dotCaCb);
		
		double ans=0;
		if (dotBaBc<0) {
			ans=calc_dist(ax, ay, bx, by);
		} else if (dotCaCb<0) {
			ans=calc_dist(ax, ay, cx, cy);
		} else {
			long crossBaBc=calc_cross(bax, bay, bcx, bcy);
			double distBc=calc_dist(bx, by, cx, cy);
//			System.out.println(cax+" "+cay+"  "+cbx+" "+cby+"  "+crossBaBc+" "+distBc);
			ans=crossBaBc/distBc;
		}
		System.out.println(ans);
	}
	static long calc_dot(int x1, int y1, int x2, int y2) {
		long ans=(long)x1*x2+(long)y1*y2;
		return ans;
	}
	static long calc_cross(int x1, int y1, int x2, int y2) {
		long ans=(long)x1*y2-(long)y1*x2;
		return Math.abs(ans);
	}
	static double calc_dist(int x1, int y1, int x2, int y2) {
		long dx=(long)x1-x2;
		long dy=(long)y1-y2;
		double ans=Math.sqrt(dx*dx+dy*dy);
		return ans;
	}
}
/*
0 5
1 1
3 0

-40 -30
-50 -10
-20 -20

1000000000 1000000000
-1000000000 -1000000000
0 -1000000000
*/
