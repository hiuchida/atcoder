import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		long sum = 0;
		if (n > k) {
			sum += k*x;
			sum += (n-k)*y;
		} else {
			sum += n*x;
		}
		System.out.println(sum);
	}
}
/*
5
3
10000
9000

2
3
10000
9000
*/
