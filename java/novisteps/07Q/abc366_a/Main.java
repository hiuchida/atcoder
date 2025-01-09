import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int t = sc.nextInt();
		int a = sc.nextInt();
		n-=t+a;
		if (t>a+n || t+n<a) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
7 4 2

99 12 48

1 0 0
*/
