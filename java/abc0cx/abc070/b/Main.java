import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		int d=sc.nextInt();
		int lt=Math.max(a, c);
		int rt=Math.min(b, d);
		int ans=Math.max(0, rt-lt);
		System.out.println(ans);
	}
}
/*
0 75 25 100

0 33 66 99

10 90 20 80
*/
