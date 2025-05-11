import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int a=sc.nextInt();
		int b=sc.nextInt();
		int k=sc.nextInt();
		int[] cnt=new int[n+1];
		for (int i=0; i<k; i++) {
			int p=sc.nextInt();
			if (a==p || b==p) {
				System.out.println("NO");
				System.exit(0);
			}
			if (cnt[p]>0) {
				System.out.println("NO");
				System.exit(0);
			}
			cnt[p]++;
		}
		System.out.println("YES");
	}
}
/*
5
1 5
3
3 4 2

7
1 3
4
2 4 2 7

4
1 4
3
2 1 3

4
1 4
3
2 4 3

20
1 4
12
2 3 5 7 8 9 10 11 12 15 13 14
*/
