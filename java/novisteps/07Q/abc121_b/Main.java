import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int c=sc.nextInt();
		int[] ary=new int[m];
		for (int i=0; i<m; i++) {
			ary[i]=sc.nextInt();
		}
		int ans=0;
		for (int j=0; j<n; j++) {
			int sum=0;
			for (int i=0; i<m; i++) {
				int a=sc.nextInt();
				sum+=a*ary[i];
			}
			if (sum+c>0) ans++;
		}
		System.out.println(ans);
	}
}
/*
2 3 -10
1 2 3
3 2 1
1 2 2

5 2 -4
-2 5
100 41
100 40
-3 0
-6 -2
18 -13

3 3 0
100 -100 0
0 100 100
100 100 100
-100 100 100
*/
