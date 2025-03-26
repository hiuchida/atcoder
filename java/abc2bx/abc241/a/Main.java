import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 10;
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int ans=0;
		for (int i=0; i<3; i++) {
			ans=ary[ans];
		}
		System.out.println(ans);
	}
}
/*
9 0 1 2 3 4 5 6 7 8

4 8 8 8 0 8 8 8 8 8

0 0 0 0 0 0 0 0 0 0
*/
