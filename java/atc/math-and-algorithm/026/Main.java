import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		double ans=0;
		for (int i=n; i>=1; i--) {
			ans+=(double)n/i;
		}
		System.out.println(ans);
	}
}
/*
5
*/
