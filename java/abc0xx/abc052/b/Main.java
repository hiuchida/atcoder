import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		int x=0;
		int ans=0;
		for (int i=0; i<n; i++) {
			char ch=s.charAt(i);
			if ('I'==ch) {
				x++;
				ans=Math.max(ans, x);
			} else {
				x--;
			}
		}
		System.out.println(ans);
	}
}
/*
5
IIDID

7
DDIDDII
*/
