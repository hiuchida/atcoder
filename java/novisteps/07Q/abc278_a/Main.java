import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		if (n<=k) {
			for (int i=0; i<n; i++) {
				System.out.print("0 ");
			}
			System.out.println();
			System.exit(0);
		}
		for (int i=k; i<n; i++) {
			System.out.print(ary[i]+" ");
		}
		for (int i=0; i<k; i++) {
			System.out.print("0 ");
		}
		System.out.println();
	}
}
/*
3 2
2 7 8

3 4
9 9 9

9 5
1 2 3 4 5 6 7 8 9
*/
