import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int x=s.charAt(0)-'0';
		int y=s.charAt(2)-'0';
		int ans=x*y;
		System.out.println(ans);
	}
}
/*
3x7

9x9

1x1
*/
