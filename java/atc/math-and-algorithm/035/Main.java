import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int x1=sc.nextInt();
		int y1=sc.nextInt();
		int r1=sc.nextInt();
		int x2=sc.nextInt();
		int y2=sc.nextInt();
		int r2=sc.nextInt();
		if (r1>r2) {
			int tmp;
			tmp=x1;
			x1=x2;
			x2=tmp;
			tmp=y1;
			y1=y2;
			y2=tmp;
			tmp=r1;
			r1=r2;
			r2=tmp;
		}
		double dist=calc_dist(x1, y1, x2, y2);
//		System.out.println(rr1+" "+rr2+" "+" "+rr12+" "+dist);
		if (r1+dist<r2) {
			System.out.println(1);
			System.exit(0);
		}
		if (r1+dist==r2) {
			System.out.println(2);
			System.exit(0);
		}
		if (r1+r2>dist) {
			System.out.println(3);
			System.exit(0);
		}
		if (r1+r2==dist) {
			System.out.println(4);
			System.exit(0);
		}
		if (r1+r2<dist) {
			System.out.println(5);
			System.exit(0);
		}
	}
	static double calc_dist(int x1, int y1, int x2, int y2) {
		long dx=(long)x1-x2;
		long dy=(long)y1-y2;
		double ans=Math.sqrt(dx*dx+dy*dy);
		return ans;
	}
}
/*
4 1 2
1 5 3

1 1 6
3 3 2

6 6 6
6 6 6
*/
