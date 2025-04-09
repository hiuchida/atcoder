import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long x=1;
		while (x<=n/x) x++;
		if (x*x>n) x--;
		long ans=x*x;
		System.out.println(ans);
	}
}
/*
10

81

271828182
*/
