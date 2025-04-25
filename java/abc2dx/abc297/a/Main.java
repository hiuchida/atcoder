import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int d=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		for (int i=1; i<n; i++) {
			if (ary[i]-ary[i-1]<=d) {
				System.out.println(ary[i]);
				System.exit(0);
			}
		}
		System.out.println(-1);
	}
}
/*
4 500
300 900 1300 1700

5 99
100 200 300 400 500

4 500
100 600 1100 1600
*/
