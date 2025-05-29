import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int x=sc.nextInt();
		int y=sc.nextInt();
		int ans=0;
		for (int i=1; i<=n; i++) {
			if (i%x==0 || i%y==0) ans++;
		}
		System.out.println(ans);
	}
}
/*
15 3 5

1000000 11 13
*/
