import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int ans = (x>y) ? x : y;
		System.out.println(ans);
	}
}
/*
10 11

100000000 10000000
*/
