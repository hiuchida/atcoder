import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int h=sc.nextInt();
		int m=sc.nextInt();
		h%=12;
		double h1=h/12.0*360.0+m/60.0/12.0*360;
		double m1=m/60.0*360;
//		System.out.println(h1+" "+m1);
		double h2=h1/360*2*Math.PI;
		double m2=m1/360*2*Math.PI;
//		System.out.println(h2+" "+m2);
		double hy=a*Math.cos(h2);
		double hx=a*Math.sin(h2);
		double my=b*Math.cos(m2);
		double mx=b*Math.sin(m2);
//		System.out.println(hy+" "+hx);
//		System.out.println(my+" "+mx);
		double ans=calc_dist(hx, hy, mx, my);
		System.out.println(ans);
	}
	static double calc_dist(double x1, double y1, double x2, double y2) {
		double dx=x1-x2;
		double dy=y1-y2;
		double ans=Math.sqrt(dx*dx+dy*dy);
		return ans;
	}
}
/*
3 4 9 0

3 4 10 40
*/
