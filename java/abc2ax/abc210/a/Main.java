import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int a=sc.nextInt();
		int x=sc.nextInt();
		int y=sc.nextInt();
		int c1=Math.min(n, a);
		int c2=Math.max(0, n-a);
		int ans=c1*x+c2*y;
		System.out.println(ans);
	}
}
/*
5 3 20 15

10 10 100 1
*/
