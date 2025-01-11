import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a=n/10;
		int b=n%10;
		if (a==9 || b==9) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
29

72

91
*/
