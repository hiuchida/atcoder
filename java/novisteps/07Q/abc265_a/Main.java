import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int n = sc.nextInt();
		int ans=0;
		if (x*3>y) {
			int b=y*(n/3);
			int a=x*(n%3);
			ans=a+b;
		} else {
			ans=x*n;
		}
		System.out.println(ans);
	}
}
/*
10 25 10

10 40 10

100 100 2

100 100 100
*/
