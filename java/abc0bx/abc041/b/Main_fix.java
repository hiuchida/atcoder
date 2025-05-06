import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int ans=Integer.MAX_VALUE;
		for (int i=1; i<=n; i++) {
			int j=n/i;
			if (i>j) break;
			int x1=j-i;
			int x2=n-i*j;
			ans=Math.min(ans, x1+x2);
		}
		System.out.println(ans);
	}
}
/*
26

41

100000
*/
