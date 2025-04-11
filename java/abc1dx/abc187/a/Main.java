import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int sa=calc(a);
		int sb=calc(b);
		int ans=Math.max(sa, sb);
		System.out.println(ans);
	}
	static int calc(int n) { //abc080_b,abc083_b,abc101_b,abc187_a: nを十進法で表したときの各桁の和
		int ans=0;
		while (n>0) {
			ans+=n%10;
			n/=10;
		}
		return ans;
	}
}
/*
123 234

593 953

100 999
*/
