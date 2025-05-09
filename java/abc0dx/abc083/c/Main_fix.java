import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long x=sc.nextLong();
		long y=sc.nextLong();
		long ans=0;
		for (long a=x; a<=y; ) {
			ans++;
			a*=2;
		}
		System.out.println(ans);
	}
}
/*
3 20

25 100

314159265 358979323846264338
*/
