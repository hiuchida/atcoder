import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		long ans=0;
		if (n>=3 && m>=3) {
			ans=(long)(n-2)*(m-2);
		} else if (n==1 && m==1) {
			ans=1;
		} else if (n==1) {
			ans=m-2;
		} else if (m==1) {
			ans=n-2;
		} else if (n==2 || m==2) {
			ans=0;
		}
		System.out.println(ans);
	}
}
/*
2 2

1 7

314 1592
*/
/*
10 1

10 2

2 10
*/
