import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int ans=0;
		for (int i=0; i<m; i++) {
			int b=sc.nextInt();
			ans+=ary[b-1];
		}
		System.out.println(ans);
	}
}
/*
3 2
10 20 30
1 3

4 1
1 1 1 100
4

8 4
22 75 26 45 72 81 47 29
4 6 7 8
*/
