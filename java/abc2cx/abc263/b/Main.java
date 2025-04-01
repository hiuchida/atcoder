import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n+1];
		for (int i=2; i<=n; i++) {
			ary[i]=sc.nextInt();
		}
		int ans=0;
		for (int i=n; i!=1; ) {
			int p=ary[i];
			ans++;
			i=p;
		}
		System.out.println(ans);
	}
}
/*
3
1 2

10
1 2 3 4 5 6 7 8 9
*/
