import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] aa=new int[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
		}
		int[] ab=new int[n];
		for (int i=0; i<n; i++) {
			ab[i]=sc.nextInt();
		}
		int lt=0;
		int rt=Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			lt=Math.max(lt, aa[i]);
			rt=Math.min(rt, ab[i]);
		}
		int ans=Math.max(0, rt-lt+1);
		System.out.println(ans);
	}
}
/*
2
3 2
7 5

3
1 5 3
10 7 3

3
3 2 5
6 9 8
*/
