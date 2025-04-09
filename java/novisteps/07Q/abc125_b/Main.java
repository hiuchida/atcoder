import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] av=new int[n];
		for (int i=0; i<n; i++) {
			av[i]=sc.nextInt();
		}
		int[] ac=new int[n];
		for (int i=0; i<n; i++) {
			ac[i]=sc.nextInt();
		}
		int ans=0;
		for (int i=0; i < 1 << n; i++) {
			int x=0;
			int y=0;
			for (int j=0; j<n; j++) {
				int mask=1 << j;
				if ((i&mask)>0) {
					x+=av[j];
					y+=ac[j];
				}
			}
			ans=Math.max(ans, x-y);
		}
		System.out.println(ans);
	}
}
/*
3
10 2 5
6 3 4

4
13 21 6 19
11 30 6 15

1
1
50
*/
