import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int ans=Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			int x=calc(ary[i]);
			ans=Math.min(ans, x);
		}
		System.out.println(ans);
	}
	static int calc(int n) {
		int ans=0;
		while (n%2==0) {
			ans++;
			n/=2;
		}
		return ans;
	}
}
/*
3
8 12 40

4
5 6 8 10

6
382253568 723152896 37802240 379425024 404894720 471526144
*/
