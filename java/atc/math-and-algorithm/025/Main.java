import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		double ans=0;
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		for (int i=0; i<n; i++) {
			int b=sc.nextInt();
			double x=(ary[i]+2.0*b)/3.0;
			ans+=x;
		}
		System.out.println(ans);
	}
}
/*
5
3 1 4 1 5
9 2 6 5 3
*/
