import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		double ans=0;
		for (int i=0; i<n; i++) {
			int p=sc.nextInt();
			int q=sc.nextInt();
			double x=1.0/p*q;
			ans+=x;
		}
		System.out.println(ans);
	}
}
/*
2
2 50
4 100
*/
