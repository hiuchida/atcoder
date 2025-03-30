import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ans=0;
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			while (a%2==0) {
				ans++;
				a/=2;
			}
		}
		System.out.println(ans);
	}
}
/*
3
5 2 4

4
631 577 243 199

10
2184 2126 1721 1800 1024 2528 3360 1945 1280 1776
*/
