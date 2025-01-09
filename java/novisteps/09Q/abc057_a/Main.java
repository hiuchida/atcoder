import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ans = (a+b) % 24;
		System.out.println(ans);
	}
}
/*
9 12

19 0

23 2
*/
