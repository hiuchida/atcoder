import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		if (2*a==b || 2*a+1==b) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
1 2

2 8

14 15
*/
