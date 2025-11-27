import java.util.*;
public class Main {
	static int n=10;
	static int[][] map=new int[n][n];
	static void set(int pos, int k) {
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (map[y][x]==0) {
					pos--;
					if (pos==0) {
						map[y][x]=af[k];
					}
				}
			}
		}
	}
	static void print() {
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				System.err.print(map[y][x]);
			}
			System.err.println();
		}
	}
	static void front() {
		for (int x=0; x<n; x++) {
			for (int y=0; y<n; y++) {
				if (map[y][x]==0) {
					for (int y2=y+1; y2<n; y2++) {
						if (map[y2][x]!=0) {
							map[y][x]=map[y2][x];
							map[y2][x]=0;
							break;
						}
					}
				}
			}
		}
	}
	static void back() {
		for (int x=0; x<n; x++) {
			for (int y=n-1; y>=0; y--) {
				if (map[y][x]==0) {
					for (int y2=y-1; y2>=0; y2--) {
						if (map[y2][x]!=0) {
							map[y][x]=map[y2][x];
							map[y2][x]=0;
							break;
						}
					}
				}
			}
		}
	}
	static void left() {
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (map[y][x]==0) {
					for (int x2=x+1; x2<n; x2++) {
						if (map[y][x2]!=0) {
							map[y][x]=map[y][x2];
							map[y][x2]=0;
							break;
						}
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		init(sc);
//		System.out.println(Arrays.toString(af));
		for (int k=0; k<N; k++) {
			int pos=sc.nextInt();
			set(pos, k);
//			print();
//			System.out.println("F");
//			System.out.println("B");
			System.out.println("L");
			System.out.flush();
//			front();
//			back();
			left();
		}
	}
	static int N=100;
	static int[] af;
	static void init(Scanner sc) {
		af=new int[N];
		for (int i=0; i<N; i++) {
			int f=sc.nextInt();
			af[i]=f;
		}
	}
}
/*



*/
