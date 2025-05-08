import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long ans=0;
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			ans+=calc(a, b);
		}
		System.out.println(ans);
	}
	static long calc(int a, int b) { //abc181_b,abc369_c: 初項a、末項bの等差数列の和
		long n=b-a+1;
		long ans=n*(a+b)/2;
		return ans;
	}
}
/*
2
1 3
3 5

3
11 13
17 47
359 44683

1
1 1000000
*/
