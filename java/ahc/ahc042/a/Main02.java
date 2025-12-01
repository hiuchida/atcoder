import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	List<String> ans=new ArrayList<>();
	void move1(char d, int p, int k) {
		for (int i=0; i<k; i++) {
			ans.add(d+" "+p);
		}
	}
	void move(char d, int p, int k) {
		move1(d, p, k);
		switch (d) {
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
	class Answer {
		char d;
		int p;
		int k;
		Answer(char d, int p, int k) {
			this.d=d;
			this.p=p;
			this.k=k;
		}
	}
	List<Answer> la=new ArrayList<>();
	void choise(char d, int p) {
		int maxv=0;
		for (int i=la.size()-1; i>=0; i--) {
			Answer ans=la.get(i);
			if (ans.d==d && ans.p==p) {
				maxv=Math.max(maxv, ans.k);
				la.remove(i);
			}
		}
		if (maxv>0) {
			move(d, p, maxv);
		}
	}
	void choise(char d) {
		for (int p=0; p<n; p++) {
			choise(d, p);
		}
	}
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		ans.clear();
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
								la.add(new Answer('U', x, vu));
							} else {
								la.add(new Answer('R', y, vr));
							}
						} else {
							if (vl<vr) {
								la.add(new Answer('L', y, vl));
							} else {
								la.add(new Answer('R', y, vr));
							}
						}
					} else {
						if (vd<vl) {
							if (vd<vr) {
								la.add(new Answer('D', x, vd));
							} else {
								la.add(new Answer('R', y, vr));
							}
						} else {
							if (vl<vr) {
								la.add(new Answer('L', y, vl));
							} else {
								la.add(new Answer('R', y, vr));
							}
						}
					}
				}
			}
		}
		choise('U');
		choise('D');
		choise('L');
		choise('R');
//		while (true) {
//			if (isTimeout()) break;
//			iteration++;
//		}
		long end=System.currentTimeMillis();
		long score=8*n*n-ans.size();
		System.err.println("--- Result ---");
		System.err.println("elaps    : " + (end-start));
		System.err.println("iteration: " + iteration);
		System.err.println("score    : " + score);
		for (String s : ans) {
			System.out.println(s);
		}
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
