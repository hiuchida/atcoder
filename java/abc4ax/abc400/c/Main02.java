import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		long p=2;
		Set<Long> set=new HashSet<>();
		for (int a=1; p<=n; a++) {
			long x=p;
			for (long b=1; x<=n/b/b; b++) {
				long y=x*b*b;
				set.add(y);
			}
			p*=2;
		}
//		System.out.println(set);
		System.out.println(set.size());
	}
}
/*
20

400

1234567890
*/
