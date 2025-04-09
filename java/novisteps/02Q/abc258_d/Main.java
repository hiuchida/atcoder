import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		int[] aa=new int[n];
		int[] ab=new int[n];
		long[] sum=new long[n+1];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
			ab[i]=sc.nextInt();
			sum[i+1]=sum[i]+aa[i]+ab[i];
		}
//		System.out.println(Arrays.toString(sum));
		long ans=Long.MAX_VALUE;
		for (int i=0; i<n; i++) {
			long c=i+1;
			long y=sum[i+1];
			if (x>c) {
				y+=(x-c)*ab[i];
			}
			ans=Math.min(ans, y);
//			System.out.println(i+" "+y+" "+ans);
		}
		System.out.println(ans);
	}
}
/*
3 4
3 4
2 3
4 2

10 1000000000
3 3
1 6
4 7
1 8
5 7
9 9
2 4
6 4
5 1
3 1
*/
