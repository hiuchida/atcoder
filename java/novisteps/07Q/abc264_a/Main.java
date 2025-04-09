import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt();
		int r = sc.nextInt();
		String s = "atcoder";
		String ans=s.substring(l-1, r);
		System.out.println(ans);
	}
}
/*
3 6

4 4

1 7
*/
