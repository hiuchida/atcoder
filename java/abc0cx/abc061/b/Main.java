import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[] ary=new int[n+1];
		for (int i=0; i<m; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			ary[a]++;
			ary[b]++;
		}
		for (int i=1; i<=n; i++) {
			System.out.println(ary[i]);
		}
	}
}
/*
4 3
1 2
2 3
1 4

2 5
1 2
2 1
1 2
2 1
1 2

8 8
1 2
3 4
1 5
2 8
3 7
5 2
4 1
6 8
*/
