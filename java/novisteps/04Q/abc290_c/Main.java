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
		int i;
		for (i=0; i<n; i++) {
			if (ans<ary[i]) break;
			ans=ary[i]+1;
		}
//		System.out.println(i);
		ans=Math.min(ans, k);
		System.out.println(ans);
	}
}
/*
7 3
2 0 2 3 2 1 9
*/
/*
7 3
2 0 2 3 2 1 4
*/
