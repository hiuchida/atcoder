import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int q=sc.nextInt();
		int[] ary=new int[n+1];
		for (int qq=0; qq<q; qq++) {
			int l=sc.nextInt();
			int r=sc.nextInt();
			int t=sc.nextInt();
			for (int j=l; j<=r; j++) ary[j]=t;
		}
		for (int i=1; i<=n; i++) {
			System.out.println(ary[i]);
		}
	}
}
/*
5 2
1 3 10
2 4 20

10 4
2 7 22
3 5 4
6 10 1
4 4 12
*/
