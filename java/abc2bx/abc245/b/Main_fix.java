import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] cnt=new int[2000+1];
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			cnt[a]++;
		}
		for (int i=0; i<=2000; i++) {
			if (cnt[i]==0) {
				System.out.println(i);
				System.exit(0);
			}
		}
	}
}
/*
8
0 3 2 6 2 1 0 0

3
2000 2000 2000
*/
