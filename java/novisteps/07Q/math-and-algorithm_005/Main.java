import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int ans=0;
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			ans+=a;
		}
		ans%=100;
		System.out.println(ans);
	}
}
/*
3
30 50 70

10
1 2 3 4 5 6 7 8 9 10

5
60 60 60 60 60
*/
