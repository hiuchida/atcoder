import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		int ans=1;
		for (int i=0; i<n; i++) {
			int a=ans*2;
			int b=ans+k;
			ans=Math.min(a, b);
		}
		System.out.println(ans);
	}
}
/*
4
3

10
10
*/
