import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int ans=0;
		for (int x=1; x<a; x++) {
			int y=a-x;
			ans=Math.max(ans, x*y);
		}
		System.out.println(ans);
	}
}
/*
10

60
*/
