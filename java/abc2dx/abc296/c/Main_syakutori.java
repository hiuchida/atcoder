import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int x=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		int j=0;
		for (int i=0; i<n; i++) {
//			System.out.println(i+" "+j+" , "+ary[i]+" "+ary[j]);
			while (j<n && ary[j]-ary[i]<x) j++;
//			System.out.println(i+" "+j+" , "+ary[i]+" "+ary[j]);
			if (j<n && ary[j]-ary[i]==x) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
6 5
3 1 4 1 5 9

6 -4
-2 -7 -1 -8 -2 -8

2 0
141421356 17320508
*/
