import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		int[] ary2=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			ary2[i]=ary[i];
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		int min=ary[n/2-1];
		int max=ary[n/2];
		for (int i=0; i<n; i++) {
			if (ary2[i]<=min) System.out.println(max);
			else System.out.println(min);
		}
	}
}
/*
4
2 4 4 3

2
1 2

6
5 5 4 4 3 3
*/
