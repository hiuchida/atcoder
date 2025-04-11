import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		long sum=0;
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			sum+=ary[i];
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(sum);
		long ans=Integer.MAX_VALUE;
		long lt=0;
		for (int i=0; i<n-1; i++) {
			lt+=ary[i];
			long rt=sum-lt;
			long x=Math.abs(lt-rt);
			ans=Math.min(ans, x);
		}
		System.out.println(ans);
	}
}
/*
6
1 2 3 4 5 6

2
10 -10
*/
