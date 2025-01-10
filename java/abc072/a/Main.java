import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int t = sc.nextInt();
		int ans=Math.max(0, x-t);
		System.out.println(ans);
	}
}
/*
100 17

48 58

1000000000 1000000000
*/
