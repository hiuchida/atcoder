import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		int ans=1;
		long m=2;
		while (m<=n) {
			ans++;
			m*=2;
		}
		ans--;
		System.out.println(ans);
	}
}
/*
6

1

1000000000000000000
*/
