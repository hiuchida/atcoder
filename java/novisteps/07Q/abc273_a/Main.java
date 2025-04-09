import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long ans=calc(n);
		System.out.println(ans);
	}
	static long calc(int n) {
		if (n==0) return 1;
		long v=calc(n-1);
		return n*v;
	}
}
/*
2

3

0

10
*/
