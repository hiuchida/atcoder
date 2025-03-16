import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] aa=new int[m+1];
		for (int i=1; i<=m; i++) {
			aa[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(aa));
		int[] ax=new int[m+1];
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=m; j++) {
				ax[j]+=sc.nextInt();
			}
		}
//		System.out.println(Arrays.toString(ax));
		for (int i=1; i<=m; i++) {
			if (aa[i]>ax[i]) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
}
/*
2 3
10 20 30
20 0 10
0 100 100

2 4
10 20 30 40
20 0 10 30
0 100 100 0
*/
