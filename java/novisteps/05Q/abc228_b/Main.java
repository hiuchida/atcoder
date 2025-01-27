import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			ary[i] = sc.nextInt()-1;
		}
		x--;
		int ans=1;
		boolean[] flag=new boolean[n];
		flag[x]=true;
		while (true) {
			x=ary[x];
			if (flag[x]) break;
			ans++;
			flag[x]=true;
		}
		System.out.println(ans);
	}
}
/*
4 2
3 1 1 2

20 12
7 11 10 1 7 20 14 2 17 3 2 5 19 20 8 14 18 2 10 10
*/
