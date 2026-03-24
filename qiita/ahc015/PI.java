import java.util.*;

public class PI {
	static void test(long max) {
		long start = System.currentTimeMillis();
		Random rand = new Random(0);
		double x;
		double y;
		long cnt = 0;
		for (int i = 0; i < max; i++) {
			x = rand.nextDouble();
			y = rand.nextDouble();
			if (x * x + y * y <= 1)
				cnt++;
		}
		double pi = (double) cnt / max * 4.0;
		long end = System.currentTimeMillis();
		System.out.println("" + (end - start) + "ms cnt=" + cnt + " max=" + max + " pi=" + pi);
	}

	public static void main(String[] args) {
		long base = 1000 * 1000;
		test(1 * base);
		test(10 * base);
		test(100 * base);
		test(1000 * base);
	}
}
