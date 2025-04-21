import java.util.*;
public class Main {
	static final long M=1000000007; //10^9+7
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long ans=1;
		for (int i=1; i<=n; i++) {
			ans*=i;
			ans%=M;
		}
		System.out.println(ans);
	}
}
/*
3

10

100000
*/
