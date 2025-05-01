import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n+1];
		for (int i=0; i<n; i++) {
			ary[i+1]=sc.nextInt();
		}
		boolean[] flag=new boolean[n+1];
		int pos=1;
		long ans=0;
		while (true) {
			if (pos==2) break;
			if (flag[pos]) {
				System.out.println(-1);
				System.exit(0);
			}
			ans++;
			flag[pos]=true;
			pos=ary[pos];
		}
		System.out.println(ans);
	}
}
/*
3
3
1
2

4
3
4
1
2

5
3
3
4
2
4
*/
