import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ax=new int[n];
		int[] ay=new int[n];
		for (int i=0; i<n; i++) {
			ax[i]=sc.nextInt();
			ay[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(ax));
//		System.out.println(Arrays.toString(ay));
		double ans=Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				double x=calc_dist(ax[i], ay[i], ax[j], ay[j]);
				ans=Math.min(ans, x);
			}
		}
		System.out.println(ans);
	}
	static double calc_dist(int x1, int y1, int x2, int y2) {
		long dx=(long)x1-x2;
		long dy=(long)y1-y2;
		double ans=Math.sqrt(dx*dx+dy*dy);
		return ans;
	}
}
/*
4
0 1
2 0
2 3
3 1
*/
