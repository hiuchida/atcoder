import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int q=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int[] sum=new int[n+1];
		for (int i=0; i<n; i++) {
			sum[i+1]=sum[i]+ary[i];
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(sum));
		for (int qq=0; qq<q; qq++) {
			int l=sc.nextInt();
			int r=sc.nextInt();
			int ans=sum[r]-sum[l-1];
			System.out.println(ans);
		}
	}
}
/*
10 5
8 6 9 1 2 1 10 100 1000 10000
2 3
1 4
3 9
6 8
1 10
*/
