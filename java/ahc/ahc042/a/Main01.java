import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	void move1(char ch, int p, int k) {
		for (int i=0; i<k; i++) {
			System.out.println(ch+" "+p);
		}
	}
	void move(char ch, int p, int k) {
		move1(ch, p, k);
		switch (ch) {
		case 'U':
			move1('D', p, k);
			break;
		case 'D':
			move1('U', p, k);
			break;
		case 'L':
			move1('R', p, k);
			break;
		case 'R':
			move1('L', p, k);
			break;
		}
	}
	int checkU(int y, int x) {
		if (map[y][x]==0) return n+1;
		for (int y2=y; y2>=0; y2--) {
			if (map[y2][x]>0) return n+1;
		}
		return y+1;
	}
	int checkD(int y, int x) {
		if (map[y][x]==0) return n+1;
		for (int y2=y; y2<n; y2++) {
			if (map[y2][x]>0) return n+1;
		}
		return n-y;
	}
	int checkL(int y, int x) {
		if (map[y][x]==0) return n+1;
		for (int x2=x; x2>=0; x2--) {
			if (map[y][x2]>0) return n+1;
		}
		return x+1;
	}
	int checkR(int y, int x) {
		if (map[y][x]==0) return n+1;
		for (int x2=x; x2<n; x2++) {
			if (map[y][x2]>0) return n+1;
		}
		return n-x;
	}
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		long score=0;
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (map[y][x]<0) {
					int vu=checkU(y, x);
					int vd=checkD(y, x);
					int vl=checkL(y, x);
					int vr=checkR(y, x);
					if (vu<vd) {
						if (vu<vl) {
							if (vu<vr) {
								move('U', x, vu);
							} else {
								move('R', y, vr);
							}
						} else {
							if (vl<vr) {
								move('L', y, vl);
							} else {
								move('R', y, vr);
							}
						}
					} else {
						if (vd<vl) {
							if (vd<vr) {
								move('D', x, vd);
							} else {
								move('R', y, vr);
							}
						} else {
							if (vl<vr) {
								move('L', y, vl);
							} else {
								move('R', y, vr);
							}
						}
					}
				}
			}
		}
//		while (true) {
//			if (isTimeout()) break;
//			iteration++;
//		}
		long end=System.currentTimeMillis();
//		System.err.println("--- Result ---");
//		System.err.println("elaps    : " + (end-start));
//		System.err.println("iteration: " + iteration);
//		System.err.println("score    : " + score);
	}
	final String DS="x.o";
	int n;
	int[][] map;
	void init(Scanner sc) {
		n=sc.nextInt();
		map=new int[n][n];
		for (int y=0; y<n; y++) {
			String s=sc.next();
			for (int x=0; x<n; x++) {
				char ch=s.charAt(x);
				int v=DS.indexOf(ch)-1;
				map[y][x]=v;
			}
		}
	}
	void print() {
		for (int y=0; y<n; y++) {
			System.out.println(Arrays.toString(map[y]));
		}
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
