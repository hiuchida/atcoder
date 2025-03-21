import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int ans=0;
		for (int i=0; i<3; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			ans+=(s*e/10);
		}
		System.out.println(ans);
	}
}
/*
50 7
40 8
30 9

990 10
990 10
990 10
*/
