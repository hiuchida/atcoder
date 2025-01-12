import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		int sum=0;
		int min=Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			sum+=ary[i];
			min=Math.min(min, ary[i]);
		}
		int ans = n;
		x-=sum;
		ans+=x/min;
		System.out.println(ans);
	}
}
/*
3 1000
120
100
140

4 360
90
90
90
90

5 3000
150
130
150
130
110
*/
