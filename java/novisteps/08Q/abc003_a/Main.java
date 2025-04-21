import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		double sum=0.0;
		double rate=1.0/n;
		for (int i=1; i<=n; i++) {
			sum += 10000*i*rate;
		}
		int ans=(int)Math.round(sum);
		System.out.println(ans);
	}
}
/*
6

91
*/
