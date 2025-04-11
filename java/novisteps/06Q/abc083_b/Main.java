import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int a=sc.nextInt();
		int b=sc.nextInt();
		long ans=0;
		for (int i=1; i<=n; i++) {
			int x=calc(i);
			if (a<=x && x<=b) ans+=i;
		}
		System.out.println(ans);
	}
	static int calc(int n) { //abc080_b,abc083_b,abc101_b: n を十進法で表したときの各桁の和
		int ans=0;
		while (n>0) {
			ans+=n%10;
			n/=10;
		}
		return ans;
	}
}
/*
20 2 5

10 1 2

100 4 16
*/
