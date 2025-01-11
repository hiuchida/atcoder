import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long a = sc.nextLong();
		long b = sc.nextLong();
		long ans = a;
		for (long i=a+1; i<=b; i++) {
			ans ^= i;
		}
		System.out.println(ans);
	}
}
