import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long a=sc.nextLong();
		long b=sc.nextLong();
		long ans=a/b;
		a%=b;
		if (a>0) ans++;
		System.out.println(ans);
	}
}
/*
7 3

123456789123456789 987654321

999999999999999998 2
*/
