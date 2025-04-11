import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int m=sc.nextInt();
		int h=sc.nextInt();
		if (h%m==0) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
10 120

10 125
*/
