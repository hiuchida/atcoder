import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=4;
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int ans=Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			ans=Math.min(ans, ary[i]);
		}
		System.out.println(ans);
	}
}
/*
5 3 7 11

100 100 1 100
*/
