import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int x=sc.nextInt();
		int y=sc.nextInt();
		int[] ax=new int[n];
		for (int i=0; i<n; i++) {
			ax[i]=sc.nextInt();
		}
		int[] ay=new int[m];
		for (int i=0; i<m; i++) {
			ay[i]=sc.nextInt();
		}
		Arrays.sort(ax);
		Arrays.sort(ay);
//		System.out.println(Arrays.toString(ax));
//		System.out.println(Arrays.toString(ay));
		int zx=Math.max(ax[n-1], x);
		int zy=Math.min(ay[0], y);
//		System.out.println(zx+" "+zy);
		if (zx<zy) System.out.println("No War");
		else System.out.println("War");
	}
}
/*
3 2 10 20
8 15 13
16 22

4 2 -48 -1
-20 -35 -91 -23
-22 66

5 3 6 8
-10 3 1 5 -100
100 6 14
*/
