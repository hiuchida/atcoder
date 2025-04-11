import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] aa=new int[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
		}
		int[] ab=new int[n];
		for (int i=0; i<n; i++) {
			ab[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(ab));
		long x=0;
		for (int i=0; i<n; i++) {
			x+=aa[i]*ab[i];
		}
//		System.out.println(x);
		if (x==0) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
2
-3 6
4 2

2
4 5
-1 -3

3
1 3 5
3 -6 3
*/
