import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l1 = sc.nextInt();
		int r1 = sc.nextInt();
		int l2 = sc.nextInt();
		int r2 = sc.nextInt();
		int lt=Math.max(l1, l2);
		int rt=Math.min(r1, r2);
		int ans=Math.max(0, rt-lt);
		System.out.println(ans);
	}
}
/*
0 3 1 5

0 1 4 5

0 3 3 7
*/
