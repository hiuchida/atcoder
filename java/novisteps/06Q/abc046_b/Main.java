import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int ans=k;		
		for (int i=1; i<n; i++) {
			ans*=(k-1);
		}
		System.out.println(ans);
	}
}
/*
2 2

1 10

10 8
*/
