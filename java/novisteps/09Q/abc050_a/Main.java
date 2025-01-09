import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		String s = sc.next();
		int b = sc.nextInt();
		long ans = 0;
		if ("+".equals(s)) ans = a + b;
		else ans = a - b;
		System.out.println(ans);
	}
}
/*
1 + 2

5 - 7
*/
