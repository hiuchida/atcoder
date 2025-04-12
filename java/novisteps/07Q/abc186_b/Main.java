import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h=sc.nextInt();
		int w=sc.nextInt();
		int[][] ary=new int[h][w];
		int min=Integer.MAX_VALUE;
		for (int y=0; y<h; y++) {
			for (int x=0; x<w; x++) {
				int a=sc.nextInt();
				ary[y][x]=a;
				min=Math.min(min, a);
			}
		}
//		for (int y=0; y<h; y++) {
//			System.out.println(Arrays.toString(ary[y]));
//		}
//		System.out.println(min);
		long ans=0;
		for (int y=0; y<h; y++) {
			for (int x=0; x<w; x++) {
				ans+=ary[y][x]-min;
			}
		}
		System.out.println(ans);
	}
}
/*
2 3
2 2 3
3 2 2

3 3
99 99 99
99 0 99
99 99 99

3 2
4 4
4 4
4 4
*/
