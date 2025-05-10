import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		long ans=calc(n, 2)+calc(m, 2);
		System.out.println(ans);
	}
	static long calc(int a, int b) { //abc159_a,abc295_d,abc350_d,abc355_d,typical90_084: aCbの組み合わせ数
		long ans=1;
		for (int i=0; i<b; i++) ans*=a-i;
		for (int i=1; i<=b; i++) ans/=i;
		return ans;
	}
}
/*
2 1

4 3

1 1

13 3

0 3
*/
