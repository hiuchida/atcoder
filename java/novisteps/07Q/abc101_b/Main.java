import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=calc(n);
		if (n%m==0) System.out.println("Yes");
		else System.out.println("No");
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
12

101

999999999
*/
