import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		int n=sc.nextInt();
		int[] ary=new int[t+1];
		for (int i=0; i<n; i++) {
			int l=sc.nextInt();
			int r=sc.nextInt();
			ary[l]++;
			ary[r]--;
		}
//		System.out.println(Arrays.toString(ary));
		int ans=0;
		for (int i=0; i<t; i++) {
			ans+=ary[i];
			System.out.println(ans);
		}
	}
}
/*
10
7
0 3
2 4
1 3
0 3
5 6
5 6
5 6
*/
