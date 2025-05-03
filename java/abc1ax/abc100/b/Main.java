import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int d=sc.nextInt();
		int n=sc.nextInt();
		int ans=1;
		while (d>0) {
			ans*=100;
			d--;
		}
		if (n==100) n++;
		ans*=n;
		System.out.println(ans);
	}
}
/*
0 5

1 11

2 85
*/
