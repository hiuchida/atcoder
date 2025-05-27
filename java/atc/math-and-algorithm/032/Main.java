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
		int lt=0;
		int rt=n-1;
		while (lt<=rt) {
			int mid=(lt+rt)>>>1;
//			System.out.println(lt+" "+rt+" "+mid+"="+ary[mid]);
			if (ary[mid]==x) {
				System.out.println("Yes");
				System.exit(0);
			} else if (ary[mid]<x) lt=mid+1;
			else rt=mid-1;
		}
		System.out.println("No");
	}
}
/*
7 3
1 2 3 4 5 6 7

7 9
1 2 3 4 5 6 7

7 1
2 3 4 5 6 7 8
*/
