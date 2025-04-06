import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int min=ary[0];
		long ans=0;
		for (int i=0; i<n; i++) {
			if (min>ary[i]) {
				ans+=min-ary[i];
			} else if (min<ary[i]) {
				min=ary[i];
			}
//			System.out.println(ary[i]+" "+min+" "+ans);
		}
		System.out.println(ans);
	}
}
/*
5
2 1 5 4 3

5
3 3 3 3 3
*/
