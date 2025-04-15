import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int a=sc.nextInt();
		int b=sc.nextInt();
		int ans=Math.min(n*a, b);
		System.out.println(ans);
	}
}
/*
4 2 9

4 2 7

4 2 8
*/
