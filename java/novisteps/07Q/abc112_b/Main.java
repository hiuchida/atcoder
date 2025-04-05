import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int t=sc.nextInt();
		int ans=Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			int c=sc.nextInt();
			int tt=sc.nextInt();
			if (t>=tt) ans=Math.min(ans, c);
		}
		if (ans==Integer.MAX_VALUE) System.out.println("TLE");
		else System.out.println(ans);
	}
}
/*
3 70
7 60
1 80
4 50

4 3
1 1000
2 4
3 1000
4 500

5 9
25 8
5 9
4 10
1000 1000
6 1
*/
