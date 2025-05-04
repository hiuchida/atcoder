import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(ary));
		int max=0;
		int ans=0;
		for (int i=0; i<n; i++) {
			max=Math.max(max, ary[i]);
			if (max<=ary[i]) ans++;
		}
		System.out.println(ans);
	}
}
/*
4
6 5 6 8

5
4 5 3 5 4

5
9 5 6 8 4
*/
