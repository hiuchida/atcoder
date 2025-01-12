import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ans=0;
		for (int i=0; i<n; i++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			ans+=(r-l+1);
		}
		System.out.println(ans);
	}
}
/*
1
24 30

2
6 8
3 3
*/
