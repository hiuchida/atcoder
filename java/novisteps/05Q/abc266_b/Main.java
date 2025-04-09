import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long m=998244353;
		while (n<0) n+=m;
		long ans=n%m;
		System.out.println(ans);
	}
}
/*
998244354

-9982443534
*/
