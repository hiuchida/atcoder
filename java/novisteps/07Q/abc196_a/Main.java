import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		int d=sc.nextInt();
		int x=Math.max(a, b);
		int y=Math.min(c, d);
		int ans=x-y;
		System.out.println(ans);
	}
}
/*
0 10
0 10

-100 -100
100 100

-100 100
-100 100
*/
