public class SquareRootUniversal {

    private static final double EPSILON = 1e-10;

    public static void main(String[] args) {
        // a < 1 のケース (0.01) と a > 1 のケース (1e20) 両方でテスト
        double[] testValues = {0.0001, 0.5, 2.0, 1e20};

        for (double a : testValues) {
            System.out.println("=== Target: sqrt(" + a + ") ===");
            solveNewton(a);
            solveBisection(a);
            System.out.println();
        }
    }

    /**
     * 汎用ニュートン法
     */
    public static void solveNewton(double a) {
        if (a < 0) return;
        if (a == 0) { System.out.println("Newton: 0.0"); return; }

        // ポイント: a < 1 のときは 1.0 を初期値にすることで、必ず「解より大きい側」からスタートできる
        double x = Math.max(1.0, a); 
        int steps = 0;

        while (true) {
            steps++;
            double nextX = 0.5 * (x + a / x);
            System.out.printf("Step %d: x = %.10e, nextX = %.10e\n", steps, x, nextX);
            if (Math.abs(nextX - x) < EPSILON) {
                x = nextX;
                break;
            }
            x = nextX;
        }
        System.out.printf("Newton Result: %.15f (Steps: %d)\n", x, steps);
    }

    /**
     * 汎用二分法
     */
    public static void solveBisection(double a) {
        if (a < 0) return;
        if (a == 0) { System.out.println("Bisection: 0.0"); return; }

        // a < 1 のときは範囲を [a, 1]、a >= 1 のときは [1, a] に設定する
        double low = Math.min(a, 1.0);
        double high = Math.max(a, 1.0);
        int steps = 0;

        while ((high - low) / high > EPSILON) {
            steps++;
            double mid = (low + high) / 2.0;
            System.out.printf("Step %d: mid = %.10e, low = %.10e, high = %.10e\n", steps, mid, low, high);
            if (mid * mid > a) {
                high = mid;
            } else {
                low = mid;
            }
        }
        System.out.printf("Bisection Result: %.15f (Steps: %d)\n", (low + high) / 2.0, steps);
    }
}
