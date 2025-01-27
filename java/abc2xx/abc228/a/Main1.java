import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		int t = sc.nextInt();
		int x = sc.nextInt();
		while (s != t) {
			if (s<=x && x<=s+1) {
				System.out.println("Yes");
				System.exit(0);
			}
			s++;
			s%=24;
		}
		System.out.println("No");
	}
}
/*
7 20 12

20 7 12

23 0 23
*/
