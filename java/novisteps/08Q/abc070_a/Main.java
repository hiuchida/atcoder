import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a=n/100;
		int c=n%10;
		if (a==c) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
575

123

812
*/
