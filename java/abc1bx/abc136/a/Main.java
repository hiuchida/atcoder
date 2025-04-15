import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		int ans=Math.max(0, c-(a-b));
		System.out.println(ans);
	}
}
/*
6 4 3

8 3 9

12 3 7
*/
