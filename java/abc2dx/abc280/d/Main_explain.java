import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int M=(int)1e6;
		long k=sc.nextLong();
		long p,a,n,x;
		long ans=1;
		for (p=2; p*p<=k; p++) {
			a=0;
			while (k%p==0) {
				k/=p;
				a++;
			}
			n=0;
			while (a>0) {
				n+=p;
				x=n;
				while (x%p==0) {
					x/=p;
					a--;
				}
			}
			ans=Math.max(ans, n);
		}
		ans=Math.max(ans, k);
		System.out.println(ans);
	}
}
/*
*/
