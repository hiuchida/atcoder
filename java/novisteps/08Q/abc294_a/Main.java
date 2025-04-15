import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		for (int i=0; i<n; i++) {
			if (ary[i]%2==0) System.out.print(ary[i]+" ");
		}
		System.out.println();
	}
}
/*
5
1 2 3 5 6

5
2 2 2 3 3

10
22 3 17 8 30 15 12 14 11 17
*/
