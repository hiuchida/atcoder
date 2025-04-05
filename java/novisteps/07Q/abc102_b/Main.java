import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		int ans=ary[n-1]-ary[0];
		System.out.println(ans);
	}
}
/*
4
1 4 6 3

2
1000000000 1

5
1 1 1 1 1
*/
