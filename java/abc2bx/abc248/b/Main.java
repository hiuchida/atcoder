import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long a = sc.nextInt();
		long b = sc.nextInt();
		long k = sc.nextInt();
		int ans=0;
		while (a<b) {
			ans++;
			a*=k;
		}
		System.out.println(ans);
	}
}
/*
1 4 2

7 7 10

31 415926 5
*/
