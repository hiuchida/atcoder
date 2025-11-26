import java.util.*;
public class Main {
	long end;
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		Answer ans=new Answer();
		ans.initRand();
		int score=ans.score();
        int iteration = 0;
		while (true) {
			if (isTimeout()) break;
			int i=rand.nextInt(d);
			int j=rand.nextInt(N)+1;
			int old=ans.at[i];
			if (old==j) continue;
			int d1=ans.undoScore(i+1);
			int d2=ans.redoScore(i+1, j);
			int v=score+d1+d2;
			ans.at[i]=j;
//			int v2=ans.score();
//			if (v!=v2) {
//				System.out.println("debug "+v+" "+v2);
//			}
//			if (score<v) {
//				score=v;
//				System.err.println("iteration: " + iteration + ", score: " + score+" "+i+" "+j);
//			} else {
//				ans.at[i]=old;
//			}
			if (score<v || isHot(v-score)) {
				score=v;
//				System.err.println("iteration: " + iteration + ", score: " + score+" "+i+" "+j);
			} else {
				ans.at[i]=old;
			}
//            if (iteration % 100000 == 0) {
//                System.err.println("iteration: " + iteration + ", score: " + score);
//            }
			iteration++;
//            double progress=(double)kk/1000/1000;
			double progress = getProgress();
			updateTemperature(progress);
		}
		end=System.currentTimeMillis();
		System.err.println("--- Result ---");
		System.err.println("elaps    : " + (end-start));
		System.err.println("iteration: " + iteration);
		System.err.println("score    : " + score);
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
		int undoScore(int i) {
			int j=at[i-1];
			int l1=0;
			int l2=0;
			int v=0;
			v-=as[i-1][j-1];
//			System.out.println(v);
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
//				System.out.println(k+" "+v1+" "+v2+" "+v);
			}
			return v;
		}
		int redoScore(int i, int j) {
			int l1=0;
			int l2=0;
			int v=0;
			v+=as[i-1][j-1];
//			System.out.println(v);
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
//				System.out.println(k+" "+v1+" "+v2+" "+v);
			}
			return v;
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
	static double current_temperature;
	static final int time_limit = 1700;
	static final double start_temperature = 2e2;
	static final double end_temperature = 1e0;
	static boolean isTimeout() {
		long lap = System.currentTimeMillis();
		long elaps = lap - start;
        return elaps >= time_limit;
	}
	static void resetTemperature() {
		current_temperature = start_temperature;
	}
	static boolean isHot(double delta) {
		double l1 = Math.abs(delta);
		double ratio = Math.exp(-l1 / current_temperature);
		return rand.nextFloat() <= ratio;
	}
	static void updateTemperature(double progress) {
        current_temperature=Math.pow(start_temperature, 1.0 - progress) * Math.pow(end_temperature, progress);
	}
	static double getProgress() {
		long lap = System.currentTimeMillis();
		long elaps = lap - start;
        return (double)elaps / time_limit;
	}
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
	}
}
/*



*/
