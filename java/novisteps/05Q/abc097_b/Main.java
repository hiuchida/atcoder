import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int ans=1;
		for (int i=2; i*i<=n; i++) {
			int x=i;
			while (x<=n) {
				x*=i;
			}
			if (x>n) x/=i;
			ans=Math.max(ans, x);
		}
		System.out.println(ans);
	}
}
/*
10

1

999
*/
