import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ans=0;
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			ans+=a;
		}
		System.out.println(ans);
	}
}
/*
3
2 7 2

1
3
*/
