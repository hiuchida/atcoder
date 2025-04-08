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
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		int ans=0;
		for (int i=0; i<k; i++) {
			ans+=ary[n-1-i];
		}
		System.out.println(ans);
	}
}
/*
5 3
1 2 3 4 5

15 14
50 26 27 21 41 7 42 35 7 5 5 36 39 1 45
*/
