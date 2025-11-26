import java.util.*;
public class Main {
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		Answer ans=new Answer();
		ans.initRand();
		int score=ans.score();
		System.err.println("score: "+score);
		ans.print();
	}
	class Answer {
		int[] at;
		Answer() {
			at=new int[d];
		}
		void initRand() {
			for (int i=0; i<d; i++) {
				at[i]=rand.nextInt(N)+1;
			}
		}
		int score() {
			int[] al=new int[N];
			int v=0;
			for (int i=0; i<d; i++) {
				int t=at[i];
				v+=as[i][t-1];
				al[t-1]=i+1;
				for (int j=0; j<N; j++) {
					v-=ac[j]*((i+1)-al[j]);
				}
//				System.out.println(v);
			}
			return v;
		}
		void print() {
			for (int i=0; i<d; i++) {
				System.out.println(at[i]);
			}
		}
		@Override
		public String toString() {
			return Arrays.toString(at);
		}
	}
	final int N=26;
	int d;
	int[] ac;
	int[][] as;
	void init(Scanner sc) {
		d=sc.nextInt();
		ac=new int[N];
		for (int i=0; i<N; i++) {
			ac[i]=sc.nextInt();
		}
		as=new int[d][N];
		for (int y=0; y<d; y++) {
			for (int x=0; x<N; x++) {
				as[y][x]=sc.nextInt();
			}
		}
//		System.out.println(Arrays.toString(ac));
//		for (int y=0; y<d; y++) {
//			System.out.println(Arrays.toString(as[y]));
//		}
	}
	static Random rand=new Random(42);
	static long start;
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
	}
}
/*



*/
