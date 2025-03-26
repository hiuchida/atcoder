import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		if (Integer.MIN_VALUE<=n && n<=Integer.MAX_VALUE) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
10

-9876543210

483597848400000
*/
