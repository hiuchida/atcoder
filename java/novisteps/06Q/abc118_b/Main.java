import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[] ary=new int[m];
		for (int i=0; i<n; i++) {
			int k=sc.nextInt();
			for (int j=0; j<k; j++) {
				int a=sc.nextInt();
				ary[a-1]++;
			}
		}
//		System.out.println(Arrays.toString(ary));
		int ans=0;
		for (int i=0; i<m; i++) {
			if (ary[i]==n) ans++;
		}
		System.out.println(ans);
	}
}
/*
3 4
2 1 3
3 1 2 3
2 3 2

5 5
4 2 3 4 5
4 1 3 4 5
4 1 2 4 5
4 1 2 3 5
4 1 2 3 4

1 30
3 5 10 30
*/
