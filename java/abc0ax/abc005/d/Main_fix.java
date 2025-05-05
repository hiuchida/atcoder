import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int[][] ary=new int[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				ary[i][j]=sc.nextInt();
			}
		}
		Aggregate2D agg = new Aggregate2D(ary);
//		System.out.println(agg);
		int q=sc.nextInt();
		for (int i=0; i<q; i++) {
			int p=sc.nextInt();
			int ans=0;
			int prey=-1;
			for (int x=Math.min(p, n); x>0; x--) {
				int y=Math.min(n, p/x);
				if (prey==y) continue;
				prey=y;
				for (int lt=0; lt+x<=n; lt++) {
					for (int tp=0; tp+y<=n; tp++) {
						ans=Math.max(ans, agg.sum(lt, tp, lt + x, tp + y));
					}
				}
			}
			System.out.println(ans);
		}
	}
	static class Aggregate2D { //Aggregate2D20250505
		int h;
		int w;
		int[][] element;
		Aggregate2D(int[][] ary) {
			h=ary.length;
			w=ary[0].length;
			element=new int[h+1][w+1];
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					element[i+1][j+1]=element[i+1][j]+element[i][j+1]-element[i][j]+ary[i][j];
				}
			}
		}
		public int sum(int lt, int tp) {
			return element[lt][tp];
		}
		public int sum(int lt, int tp, int rt, int bm) {
			return element[rt][bm]-element[rt][tp]-element[lt][bm]+element[lt][tp];
		}
		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder();
			for (int i=0; i<h+1; i++) {
				for (int j=0; j<w+1; j++) {
					if (j>0) sb.append(" ");
					sb.append(element[i][j]);
				}
				sb.append(System.getProperty("line.separator"));
			}
			return sb.toString();
		}
	}
}
/*
3
3 2 1
2 2 1
1 1 1
3
1
4
9

3
1 1 1
1 1 1
9 9 9
1
4
*/
