import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		long sum=0;
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
			sum+=ary[i];
		}
		long x = sc.nextLong();
		long ans = 0;
		if (x>sum) {
			ans+=x/sum*n;
			x%=sum;
		}
		for (int i=0; i<n; i++) {
			if (x<0) break;
			ans++;
			x-=ary[i];
		}
		System.out.println(ans);
	}
}
/*
3
3 5 2
26

4
12 34 56 78
1000
*/
