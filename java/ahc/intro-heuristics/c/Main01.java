import java.util.*;
public class Main {
	int[] at;
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		at=new int[d];
		for (int i=0; i<d; i++) {
			at[i]=sc.nextInt();
		}
		int score=score();
		int m=sc.nextInt();
		for (int k=0; k<m; k++) {
			int i=sc.nextInt();
			int j=sc.nextInt();
			int d1=undoScore(i);
			int d2=redoScore(i, j);
			at[i-1]=j;
			int v=score();
			int v2=score+d1+d2;
			if (v!=v2) {
				System.err.println(v+" "+v2+" "+score+" "+d1+" "+d2);
			}
			score=v2;
			System.out.println(score);
		}
	}
	int undoScore(int i) {
		int j=at[i-1];
		int l1=0;
		int l2=0;
		int v=0;
		v-=as[i-1][j-1];
//		System.out.println(v);
		for (int k=0; k<d; k++) {
			int t=at[k];
			if (j==t) {
				l1=k+1;
			}
			if (i-1==k) t=-1;
			if (j==t) {
				l2=k+1;
			}
			int v1=ac[j-1]*((k+1)-l1);
			int v2=ac[j-1]*((k+1)-l2);
			v+=v1-v2;
//			System.out.println(k+" "+v1+" "+v2+" "+v);
		}
		return v;
	}
	int redoScore(int i, int j) {
		int l1=0;
		int l2=0;
		int v=0;
		v+=as[i-1][j-1];
//		System.out.println(v);
		for (int k=0; k<d; k++) {
			int t=at[k];
			if (j==t) {
				l1=k+1;
			}
			if (i-1==k) t=j;
			if (j==t) {
				l2=k+1;
			}
			int v1=ac[j-1]*((k+1)-l1);
			int v2=ac[j-1]*((k+1)-l2);
			v+=v1-v2;
//			System.out.println(k+" "+v1+" "+v2+" "+v);
		}
		return v;
	}
	int score() {
		int[] al=new int[N];
		int v=0;
		for (int k=0; k<d; k++) {
			int t=at[k];
			v+=as[k][t-1];
			al[t-1]=k+1;
			for (int j=0; j<N; j++) {
				v-=ac[j]*((k+1)-al[j]);
			}
//			System.out.println(v);
		}
		return v;
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
