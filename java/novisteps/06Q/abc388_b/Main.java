import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int d = sc.nextInt();
		int[] at=new int[n];
		int[] al=new int[n];
		for (int i=0; i<n; i++) {
			at[i]=sc.nextInt();
			al[i]=sc.nextInt();
		}
		for (int i=1;i<=d;i++) {
			int max=0;
			for (int j=0;j<n;j++) {
				int w=at[j]*(al[j]+i);
				max=Math.max(max, w);
			}
			System.out.println(max);
		}
	}
}
/*
4 3
3 3
5 1
2 4
1 10

1 4
100 100
*/
