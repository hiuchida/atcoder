import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int ans=Math.max(0, a-2*b);
		System.out.println(ans);
	}
}
/*
12 4

20 15

20 30
*/
