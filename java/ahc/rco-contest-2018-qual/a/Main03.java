import java.util.*;
public class Main {
	Set<String> set=new HashSet<>();
	int[] make() {
		int[] ai=new int[k];
		boolean[] flag=new boolean[n];
		for (int i=0; i<k; i++) {
			while (true) {
				int v=rand.nextInt(n);
				if (flag[v]) continue;
				flag[v]=true;
				ai[i]=v;
				break;
			}
		}
		Arrays.sort(ai);
		return ai;
	}
	String key(int[] ai) {
		StringBuilder sb=new StringBuilder();
		for (int i=0; i<k; i++) {
			if (i>0) sb.append(",");
			sb.append(ai[i]);
		}
		return sb.toString();
	}
	String makecmd() {
		StringBuilder sb=new StringBuilder();
		for (int i=0; i<t; i++) {
			int d=rand.nextInt(DS.length());
			char ch=DS.charAt(d);
			sb.append(ch);
		}
		return sb.toString();
	}
	void check(int[] ai) {
		for (int d=0; d<DS.length(); d++) {
			String cmd=makecmd();
			int sum=0;
			for (int j=0; j<k; j++) {
				int v=map[ai[j]].score(cmd);
				sum+=v;
			}
			if (maxv<sum) {
				maxv=sum;
				maxai=Arrays.copyOf(ai, k);
				maxcmd=cmd;
			}
		}
//		System.out.println(Arrays.toString(ai)+" "+Arrays.toString(as)+" "+sum);
	}
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		long end;
		while (true) {
			end=System.currentTimeMillis();
			if (end-start>=time_limit) break;
			int[] ai=make();
			String key=key(ai);
			if (set.contains(key)) continue;
			set.add(key);
			check(ai);
		}
		end=System.currentTimeMillis();
		System.err.println("elaps:"+(end-start));
		System.err.println("score:"+maxv);
//		System.out.println(Arrays.toString(maxai)+" "+maxcmd+" "+maxv);
		for (int i=0; i<k; i++) {
			if (i>0) System.out.print(" ");
			System.out.print(maxai[i]);
		}
		System.out.println();
		System.out.println(maxcmd);
	}
	int maxv=-1;
	int[] maxai;
	String maxcmd;
	class Map {
		final String SC="#ox@";
		char[][] ary;
		int py;
		int px;
		Map(int h, int w, Scanner sc) {
			this.ary=new char[h][w];
			for (int y=0; y<h; y++) {
				String s=sc.next();
				for (int x=0; x<w; x++) {
					char ch=s.charAt(x);
					if (ch=='@') {
						py=y;
						px=x;
					}
					ary[y][x]=ch;
//					int idx=SC.indexOf(ch);
//					ary[y][x]=idx;
				}
			}
		}
		int score(String cmd) {
			int y=py;
			int x=px;
			int v=0;
			boolean[][] flag=new boolean[h][w];
			for (int i=0; i<cmd.length(); i++) {
				char ch=cmd.charAt(i);
				int d=DS.indexOf(ch);
				int y2=y+DY[d];
				int x2=x+DX[d];
				switch (ary[y2][x2]) {
				case '#':
					break;
				case 'o':
					if (!flag[y2][x2]) {
						v++;
						flag[y2][x2]=true;
					}
					y=y2;
					x=x2;
					break;
				case 'x':
					return v;
				case '@':
					y=y2;
					x=x2;
					break;
				}
			}
			return v;
		}
		void print() {
			System.out.println(py+" "+px);
			for (int y=0; y<h; y++) {
				for (int x=0; x<w; x++) {
					System.out.print(ary[y][x]);
				}
				System.out.println();
			}
		}
	}
	int n;
	int k;
	int h;
	int w;
	int t;
	Map[] map;
	void init(Scanner sc) {
		n=sc.nextInt();
		k=sc.nextInt();
		h=sc.nextInt();
		w=sc.nextInt();
		t=sc.nextInt();
		map=new Map[n];
		for (int i=0; i<n; i++) {
			map[i]=new Map(h, w, sc);
//			map[i].print();
//			System.out.println("---");
		}
	}
	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	static final String DS = "UDLR";
	static Random rand=new Random(42);
	static long start;
	static final int time_limit=3800;
	public static void main(String[] args) {
		start=System.currentTimeMillis();
		new Main().solve();
	}
}
/*



*/
