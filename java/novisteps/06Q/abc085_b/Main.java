import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
		int ans=1;
		for (int i=n-1; i>0; i--) {
			if (ary[i-1]<ary[i]) ans++;
		}
		System.out.println(ans);
	}
}
/*
4
10
8
8
6

3
15
15
15

7
50
30
50
100
50
80
30
*/
