import java.util.*;
public class Main {
	int[] at;
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		at=new int[d];
		for (int i=0; i<d; i++) {
			at[i]=rand.nextInt(N)+1;
		}
//		System.out.println(Arrays.toString(at));
		int v=0;
		for (int i=0; i<d; i++) {
			int t=at[i];
			v+=as[i][t-1];
			al[t-1]=i+1;
			for (int j=0; j<N; j++) {
				v-=ac[j]*((i+1)-al[j]);
			}
//			System.out.println(v);
		}
		System.err.println("score: "+v);
		for (int i=0; i<d; i++) {
			System.out.println(at[i]);
		}
	}
	final int N=26;
	int d;
	int[] ac;
	int[][] as;
	int[] al;
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
		al=new int[N];
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
