import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int x=sc.nextInt();
		int t=sc.nextInt();
		int c=n/x;
		if (n%x!=0) c++;
		int ans=c*t;
		System.out.println(ans);
	}
}
/*
20 12 6

1000 1 1000
*/
