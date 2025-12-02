import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		System.out.println();
		long score=0;
		while (true) {
			if (isTimeout()) break;
			iteration++;
		}
		long end=System.currentTimeMillis();
		System.err.println("--- Result ---");
		System.err.println("elaps    : " + (end-start));
		System.err.println("iteration: " + iteration);
		System.err.println("score    : " + score);
	}
	final int N=100;
	int n;
	int m;
	int k;
	int t;
	int s;
	void init(Scanner sc) {
		n=sc.nextInt();
		m=sc.nextInt();
		k=sc.nextInt();
		t=sc.nextInt();
		s=sc.nextInt();
	}
	static Random rand=new Random(42);
	static long start;
	static int iteration = 0;
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
