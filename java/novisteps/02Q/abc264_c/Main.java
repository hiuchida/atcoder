import java.util.*;
public class Main {
	static int h1;
	static int w1;
	static int[][] ary1;
	static int h2;
	static int w2;
	static int[][] ary2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		h1 = sc.nextInt();
		w1 = sc.nextInt();
		ary1=new int[h1][w1];
		for (int i=0; i<h1; i++) {
			for (int j=0; j<w1; j++) {
				ary1[i][j]=sc.nextInt();
			}
		}
		h2 = sc.nextInt();
		w2 = sc.nextInt();
		ary2=new int[h2][w2];
		for (int i=0; i<h2; i++) {
			for (int j=0; j<w2; j++) {
				ary2[i][j]=sc.nextInt();
			}
		}
//		System.out.println("[A]");
//		for (int i=0; i<h1; i++) {
//			System.out.println(Arrays.toString(ary1[i]));
//		}
//		System.out.println("[B]");
//		for (int i=0; i<h2; i++) {
//			System.out.println(Arrays.toString(ary2[i]));
//		}
		for (int i=0; i < 1 << h1; i++) {
			if (Integer.bitCount(i)!=h2) continue;
			int[][] ary3=new int[h2][w1];
			int idx=0;
			for (int j=0; j<h1; j++) {
				int mask=1 << j;
				if ((i&mask)>0) {
					System.arraycopy(ary1[j], 0, ary3[idx], 0, w1);
					idx++;
				}
			}
//			System.out.println("No."+i);
//			for (int j=0; j<h2; j++) {
//				System.out.println(Arrays.toString(ary3[j]));
//			}
			if (check(ary3, ary2)) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
	static boolean check(int[][] ary3, int[][] ary2) {
		for (int i=0; i < 1 << w1; i++) {
			if (Integer.bitCount(i)!=w2) continue;
			int idx=0;
			boolean bok=true;
			for (int j=0; j<w1; j++) {
				int mask=1 << j;
				if ((i&mask)>0) {
					for (int k=0; k<h2; k++) {
						if (ary3[k][j]!=ary2[k][idx]) bok=false;
					}
					idx++;
				}
			}
			if (bok) return true;
		}
		return false;
	}
}
/*
4 5
1 2 3 4 5
6 7 8 9 10
11 12 13 14 15
16 17 18 19 20
2 3
6 8 9
16 18 19

3 3
1 1 1
1 1 1
1 1 1
1 1
2
*/
/*
3 3
1 1 1
1 1 1
1 1 1
1 1
1
*/
