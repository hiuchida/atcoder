import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	static int n=10;
	static Random rand=new Random(42);
	static Board board=new Board();
	static double simu(int d, Board b, int k) {
		long score=b.score(k);
//		System.err.println(DS.charAt(d)+" "+k+" "+score);
		return score;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		init(sc);
//		System.out.println(Arrays.toString(af));
		for (int k=0; k<N; k++) {
			int pos=sc.nextInt();
			board.set(pos, k);
//			print();
			Board[] ab=new Board[DS.length()];
			double[] as=new double[DS.length()];
			for (int d=0; d<DS.length(); d++) {
				ab[d]=board.copy();
			}
			for (int d=0; d<DS.length(); d++) {
				ab[d].incline(DS.charAt(d));
			}
			for (int c=0; c<100; c++) {
				for (int d=0; d<DS.length(); d++) {
					as[d]+=simu(d, ab[d], k+1);
				}
			}
//			System.err.println(k+" "+Arrays.toString(as));
			double maxv=-1;
			int maxi=-1;
			for (int d=0; d<DS.length(); d++) {
				if (maxv<as[d]) {
					maxv=as[d];
					maxi=d;
				}
			}
			board.restore(ab[maxi]);
			System.out.println(DS.charAt(maxi));
			System.out.flush();
		}
		long score=board.score();
//		System.err.println("base="+base);
		score*=1000*1000*10;
		score/=base;
		score+=5;
		score/=10;
		if (!RELEASE) System.err.println("score="+score);
	}
	static class Board {
		int[][] map;
		Board() {
			this.map=new int[n][n];
		}
		Board(Board that) {
			this.map=new int[n][];
			for (int y=0; y<n; y++) {
				map[y]=Arrays.copyOf(that.map[y], n);
			}
		}
		void set(int pos, int k) {
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
		void incline(char d) {
			switch (d) {
			case 'F':
				front();
				break;
			case 'B':
				back();
				break;
			case 'L':
				left();
				break;
			case 'R':
				right();
				break;
			}
		}
		void front() {
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
		void back() {
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
		void left() {
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
		void right() {
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
		Board copy() {
			return new Board(this);
		}
		void restore(Board that) {
			for (int y=0; y<n; y++) {
				map[y]=that.map[y];
			}
		}
		long score() {
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
		void print() {
			for (int y=0; y<n; y++) {
				for (int x=0; x<n; x++) {
					System.err.print(map[y][x]);
				}
				System.err.println();
			}
		}
		long score(int nk) {
			for (int k=nk; k<N; k++) {
				int nxt=af[nk];
				int pos=rand.nextInt(101-k);
				set(pos, k);
				int d=rand.nextInt(DS.length());
				incline(DS.charAt(d));
			}
			long score=score();
			return score;
		}
		int move(char d, int y, int x) {
			switch (d) {
			case 'F':
				return movef(y, x);
			case 'B':
				return moveb(y, x);
			case 'L':
				return movel(y, x);
			case 'R':
				return mover(y, x);
			}
			return 0;
		}
		int movef(int y, int x) {
			int d=1;
			for (; true; d++) {
				int y2=y-d;
				if (y2<0 || map[y2][x]!=0) break;
			}
			d--;
			return (y-d)*n+x;
		}
		int moveb(int y, int x) {
			int d=1;
			for (; true; d++) {
				int y2=y+d;
				if (y2>=n || map[y2][x]!=0) break;
			}
			d--;
			return (y+d)*n+x;
		}
		int movel(int y, int x) {
			int d=1;
			for (; true; d++) {
				int x2=x-d;
				if (x2<0 || map[y][x2]!=0) break;
			}
			d--;
			return y*n+x-d;
		}
		int mover(int y, int x) {
			int d=1;
			for (; true; d++) {
				int x2=x+d;
				if (x2>=n || map[y][x2]!=0) break;
			}
			d--;
			return y*n+x+d;
		}
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
	static final int[] DY = { -1, 1, 0, 0 }; //F,B,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //F,B,L,R
	static final String DS = "FBLR";
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
