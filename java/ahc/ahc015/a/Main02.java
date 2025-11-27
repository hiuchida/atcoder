import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
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
	static double score(int[][] ary, int nk) {
		long score=score(ary);
		if (nk==N) return score;
		int nxt=af[nk];
		int cnt=1;
		long sum=score;
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (ary[y][x]==0) {
					ary[y][x]=nxt;
					long v=score(ary);
					cnt++;
					sum+=v;
					ary[y][x]=0;
				}
			}
		}
		return (double)sum/cnt;
	}
	static long score(int[][] ary) {
		UnionFind uf=new UnionFind(N);
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (ary[y][x]==0) continue;
				for (int d=0; d<DS.length(); d++) {
					int y2=y+DY[d];
					int x2=x+DX[d];
					if (0<=y2 && y2<n && 0<=x2 && x2<n) {
						if (ary[y][x]==ary[y2][x2]) {
							uf.merge(y*n+x, y2*n+x2);
						}
					}
				}
			}
		}
		long score=0;
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (ary[y][x]==0) continue;
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
	static void front(int[][] ary) {
		for (int x=0; x<n; x++) {
			for (int y=0; y<n; y++) {
				if (ary[y][x]==0) {
					for (int y2=y+1; y2<n; y2++) {
						if (ary[y2][x]!=0) {
							ary[y][x]=ary[y2][x];
							ary[y2][x]=0;
							break;
						}
					}
				}
			}
		}
	}
	static void back(int[][] ary) {
		for (int x=0; x<n; x++) {
			for (int y=n-1; y>=0; y--) {
				if (ary[y][x]==0) {
					for (int y2=y-1; y2>=0; y2--) {
						if (ary[y2][x]!=0) {
							ary[y][x]=ary[y2][x];
							ary[y2][x]=0;
							break;
						}
					}
				}
			}
		}
	}
	static void left(int[][] ary) {
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (ary[y][x]==0) {
					for (int x2=x+1; x2<n; x2++) {
						if (ary[y][x2]!=0) {
							ary[y][x]=ary[y][x2];
							ary[y][x2]=0;
							break;
						}
					}
				}
			}
		}
	}
	static void right(int[][] ary) {
		for (int y=0; y<n; y++) {
			for (int x=n-1; x>=0; x--) {
				if (ary[y][x]==0) {
					for (int x2=x-1; x2>=0; x2--) {
						if (ary[y][x2]!=0) {
							ary[y][x]=ary[y][x2];
							ary[y][x2]=0;
							break;
						}
					}
				}
			}
		}
	}
	static int[][] copy() {
		int[][] ans=new int[n][];
		for (int y=0; y<n; y++) {
			ans[y]=Arrays.copyOf(map[y], n);
		}
		return ans;
	}
	static void restore(int[][] ary) {
		for (int y=0; y<n; y++) {
			map[y]=ary[y];
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
			double[] as=new double[DS.length()];
			int[][] mapf=copy();
			front(mapf);
			as[0]=score(mapf, k+1);
			int[][] mapb=copy();
			back(mapb);
			as[1]=score(mapb, k+1);
			int[][] mapl=copy();
			left(mapl);
			as[2]=score(mapl, k+1);
			int[][] mapr=copy();
			right(mapr);
			as[3]=score(mapr, k+1);
//			System.err.println(Arrays.toString(as));
			double maxv=-1;
			int maxi=-1;
			for (int d=0; d<DS.length(); d++) {
				if (maxv<as[d]) {
					maxv=as[d];
					maxi=d;
				}
			}
			switch (maxi) {
			case 0:
				System.out.println("F");
				restore(mapf);
				break;
			case 1:
				System.out.println("B");
				restore(mapb);
				break;
			case 2:
				System.out.println("L");
				restore(mapl);
				break;
			case 3:
				System.out.println("R");
				restore(mapr);
				break;
			}
			System.out.flush();
		}
		long score=score(map);
//		System.err.println("base="+base);
		score*=1000*1000*10;
		score/=base;
		score+=5;
		score/=10;
		if (!RELEASE) System.err.println("score="+score);
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
