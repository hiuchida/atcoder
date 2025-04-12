import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int x=sc.nextInt();
		x*=100;
		long s=0;
		for (int i=0; i<n; i++) {
			int v=sc.nextInt();
			int p=sc.nextInt();
			s+=v*p;
			if (x<s) {
				System.out.println(i+1);
				System.exit(0);
			}
		}
		System.out.println(-1);
	}
}
/*
2 15
200 5
350 3

2 10
200 5
350 3

3 1000000
1000 100
1000 100
1000 100
*/
