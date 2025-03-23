import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int k = sc.nextInt();
		int s = sc.nextInt();
		int t = sc.nextInt();
		int ans=a*s+b*t;
		int cnt=s+t;
		if (cnt>=k) ans-=c*cnt;
		System.out.println(ans);
	}
}
/*
100 200 50 20
40 10

400 1000 400 21
10 10

400 1000 400 20
10 10
*/
