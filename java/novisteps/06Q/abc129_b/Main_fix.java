import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Aggregate agg = new Aggregate(ary);
//		System.out.println(agg);
		int ans=Integer.MAX_VALUE;
		for (int t=1; t<n; t++) {
			int s1=agg.sum(0, t);
			int s2=agg.sum(t, n);
			int dif=Math.abs(s1-s2);
			ans=Math.min(ans, dif);
		}
		System.out.println(ans);
	}
	static class Aggregate { //Aggregate20250505
		int n;
		int[] element;
		Aggregate(int[] ary) {
			n=ary.length;
			element=new int[n+1];
			for (int i=0; i<n; i++) {
				element[i+1]=element[i]+ary[i];
			}
		}
		int sum(int k) {
			return element[k];
		}
		int sum(int lt, int rt) {
			return element[rt]-element[lt];
		}
		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder();
			for (int i=0; i<n+1; i++) {
				if (i>0) sb.append(" ");
				sb.append(element[i]);
			}
			return sb.toString();
		}
	}
}
/*
3
1 2 3

4
1 3 1 1

8
27 23 76 2 3 5 62 52
*/
