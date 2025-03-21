import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
		for (int i=0; i<q; i++) {
			int x = sc.nextInt();
			int a=Arrays.binarySearch(ary, x);
			if (a>=0) {
				System.out.println(n-a);
			} else {
				a=~a;
				System.out.println(n-a);
			}
		}
	}
}
/*
3 1
100 160 130
120

5 5
1 2 3 4 5
6
5
4
3
2

5 5
804289384 846930887 681692778 714636916 957747794
424238336
719885387
649760493
596516650
189641422
*/
