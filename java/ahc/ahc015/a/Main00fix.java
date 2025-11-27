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
	static long score() {
		UnionFind uf=new UnionFind(N);
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (map[y][x]==0) continue;
				for (int d=0; d<DS.length(); d++) {
					int y2=y+DY[d];
					int x2=x+DX[d];
					if (0<=y2 && y2<n && 0<=x2 && x2<n) {
						if (map[y][x]==map[y2][x2]) {
							uf.merge(y*n+x, y2*n+x2);
						}
					}
				}
			}
		}
		long score=0;
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (map[y][x]==0) continue;
				if (uf.root(y*n+x)==y*n+x) {
					int v=uf.size(y*n+x);
					score+=v*v;
				}
			}
		}
		return score;
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
	static void right() {
		for (int y=0; y<n; y++) {
			for (int x=n-1; x>=0; x--) {
				if (map[y][x]==0) {
					for (int x2=x-1; x2>=0; x2--) {
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
//			System.out.println("L");
			System.out.println("R");
			System.out.flush();
//			front();
//			back();
//			left();
			right();
		}
		long score=score();
		System.err.println("base="+base);
		score*=1000*1000*10;
		score/=base;
		score+=5;
		score/=10;
		System.err.println("score="+score);
	}
	static int N=100;
	static int[] af;
	static int base=0;
	static void init(Scanner sc) {
		int[] cnt=new int[3];
		af=new int[N];
		for (int i=0; i<N; i++) {
			int f=sc.nextInt();
			af[i]=f;
			cnt[f-1]++;
		}
		for (int i=0; i<3; i++) {
			base+=cnt[i]*cnt[i];
		}
	}
	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	static final String DS = "UDLR";
	static class UnionFind { //UnionFind20250102
		int[] uf;
		public UnionFind(int n) {
			uf = new int[n];
			for (int i=0; i<n; i++) uf[i] = -1;
		}
		public int root(int v) {
			if (uf[v] < 0) return v;
			uf[v] = root(uf[v]);
			return uf[v];
		}
		public void merge(int u, int v) {
			u = root(u);
			v = root(v);
			if (u == v) return;
			int uw = uf[u];
			int vw = uf[v];
			if (uw <= vw) {
				uf[u] += uf[v];
				uf[v] = u;
			} else if (uw > vw) {
				uf[v] += uf[u];
				uf[u] = v;
			}
		}
		public boolean same(int u, int v) {
			return root(u) == root(v);
		}
		public int size(int v) {
			v = root(v);
			return -uf[v];
		}
		@Override
		public String toString() {
			return Arrays.toString(uf);
		}
	}
}
/*



*/
