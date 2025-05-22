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
		System.out.println(ans);
	}
}
/*
5
3 1 4 1 5

3
10 20 50

10
1 2 3 4 5 6 7 8 9 10
*/
