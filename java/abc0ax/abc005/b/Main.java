import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ans=Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			int t = sc.nextInt();
			ans=Math.min(ans, t);
		}
		System.out.println(ans);
	}
}
/*
3
1
2
3

3
3
3
3

5
3
1
4
1
5
*/
