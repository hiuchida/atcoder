import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ans=0;
		for (int i=0; i<n-1; i++) {
			int a = sc.nextInt();
			ans+=a;
		}
		System.out.println(-ans);
	}
}
/*
4
1 -2 -1

3
0 0

6
10 20 30 40 50
*/
