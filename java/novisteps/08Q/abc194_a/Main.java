import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=a+b;
		int ans=0;
		if (c>=15 && b>=8) ans=1;
		else if (c>=10 && b>=3) ans=2;
		else if (c>=3) ans=3;
		else ans=4;
		System.out.println(ans);
	}
}
/*
10 8

1 2

0 0
*/
