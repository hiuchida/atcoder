import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long sb=0;
		long sr=0;
		for (int i=0; i<n; i++) {
			int b=sc.nextInt();
			sb+=b;
		}
		for (int i=0; i<n; i++) {
			int r=sc.nextInt();
			sr+=r;
		}
		double ab=(double)sb/n;
		double ar=(double)sr/n;
		double ans=ab+ar;
		System.out.println(ans);
	}
}
/*
3
1 2 3
10 20 30
*/
