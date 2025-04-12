import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int v=sc.nextInt();
		int t=sc.nextInt();
		int s=sc.nextInt();
		int d=sc.nextInt();
		int tt=v*t;
		int ss=v*s;
//		System.out.println(tt+" "+ss+" "+d);
		if (tt<=d && d<=ss) System.out.println("No");
		else System.out.println("Yes");
	}
}
/*
10 3 5 20

10 3 5 30
*/
