import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n+2];
		int sum=0;
		for (int i=1; i<=n+1; i++) {
			if (i<=n) ary[i]=sc.nextInt();
			sum+=Math.abs(ary[i]-ary[i-1]);
		}
//		System.out.println(Arrays.toString(ary)+" "+sum);
		for (int i=1; i<=n; i++) {
			int d1=Math.abs(ary[i]-ary[i-1]);
			int d2=Math.abs(ary[i+1]-ary[i]);
			int d3=Math.abs(ary[i+1]-ary[i-1]);
			int ans=sum-d1-d2+d3;
			System.out.println(ans);
		}
	}
}
/*
3
3 5 -1

5
1 1 1 2 0

6
-679 -2409 -3258 3095 -3291 -4462
*/
