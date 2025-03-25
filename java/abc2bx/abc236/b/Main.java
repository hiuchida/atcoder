import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] cnt=new int[n+1];
		for (int i=0; i<4*n-1; i++) {
			int a=sc.nextInt();
			cnt[a]++;
		}
		for (int i=1; i<=n; i++) {
			if (cnt[i]!=4) {
				System.out.println(i);
				System.exit(0);
			}
		}
	}
}
/*
3
1 3 2 3 3 2 2 1 1 1 2

1
1 1 1

4
3 2 1 1 2 4 4 4 4 3 1 3 2 1 3
*/
