import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		StringBuilder sb=new StringBuilder();
		for (long i=2; i*i<=n; i++) {
			while (n%i==0) {
				sb.append(i).append(" ");
				n/=i;
			}
		}
		if (n>1) sb.append(n).append(" ");
		String ans=sb.toString().trim();
		System.out.println(ans);
	}
}
/*
10

36
*/
