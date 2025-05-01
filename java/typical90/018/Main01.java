import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		int l=sc.nextInt();
		int x=sc.nextInt();
		int y=sc.nextInt();
		int q=sc.nextInt();
		for (int qq=0; qq<q; qq++) {
			int e=sc.nextInt();
			double ans=calc(t, l, x, y, e);
			System.out.println(ans);
		}
	}
	static double calc(int t, int l, int x, int y, int e) {
		double rad=2*Math.PI*e/t;
		double my=-l*Math.sin(rad)/2;
		double mz=-l*Math.cos(rad)/2+l/2;
		double xx=x;
		double yy=y-my;
		double w=Math.sqrt(xx*xx+yy*yy);
		double rad2=Math.atan2(mz, w);
		double ans=rad2*360/2/Math.PI;
		return ans;
	}
}
/*
4
2 1 1
4
0
1
2
3

5121
312000000 4123 3314
6
123
12
445
4114
42
1233
*/
