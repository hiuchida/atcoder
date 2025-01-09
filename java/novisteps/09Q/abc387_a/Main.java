import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		long ans = a+b;
		ans *= ans;
		System.out.println(ans);
	}
}
/*
20 25

30 25

45 11

2025 1111
*/
