import java.util.*;
public class Main {
	static void DEBUG(Object x) {
//		System.out.println(x);
	}
	static void DEBUG(long x) {
		DEBUG(""+x);
    }
	static void DEBUG(int[] x) {
		DEBUG(Arrays.toString(x));
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int[][] ary = new int[h][w];
		int[] sumx = new int[h];
		int[] sumy = new int[w];
		for (int y=0; y<h; y++) {
			for (int x=0; x<w; x++) {
				int a = sc.nextInt();
				ary[y][x] = a;
				sumx[y] += a;
				sumy[x] += a;
			}
		}
		DEBUG(sumx);
		DEBUG(sumy);
		long ans = 0;
		for (int y=0; y<h; y++) {
			for (int x=0; x<w; x++) {
				ans = sumx[y] + sumy[x] - ary[y][x];
				if (x > 0) System.out.print(" ");
				System.out.print(ans);
			}
			System.out.println();
		}
	}
}
