import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int s=sc.nextInt();
		int ans=0;
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				if (i+j<=s) ans++;
			}
		}
		System.out.println(ans);
	}
}
/*
3 4

869 120
*/
