import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		double ans=0;
		boolean bplus=true;
		for (int i=n-1; i>=0; i--) {
			int sign=bplus ? 1 : -1;
			ans+=sign*Math.PI*ary[i]*ary[i];
			bplus=!bplus;
		}
		System.out.println(ans);
	}
}
/*
3
1
2
3

6
15
2
3
7
6
9
*/
