public class FastSquareRoot {

    public static void main(String[] args) {
        double a = 1e20; // 巨大な数
        // double a = 0.0001; // 小さな数でもOK

        System.out.println("Target: sqrt(" + a + ") = " + Math.sqrt(a));
        solveFastNewton(a);
    }

    public static void solveFastNewton(double a) {
        if (a <= 0) return;

        // --- 1. ビット演算による初期値の推定 ---
        // IEEE 754 形式のビットパターンを取得
        long bits = Double.doubleToLongBits(a);
        
        // 指数部を半分にする魔法の操作
        // 指数部はバイアス(1023)がかかっているので、それを考慮してビットをずらす
        long initialBits = (bits >> 1) + (511L << 52); 
        
        double x = Double.longBitsToDouble(initialBits);
        System.out.printf("Estimated Initial x0: %.10e\n", x);

        // --- 2. ニュートン法で仕上げ ---
        // 初期値が極めて正確なので、わずか数回で終わる
        int steps = 0;
        double epsilon = 1e-15;
        
        while (true) {
            steps++;
            double nextX = 0.5 * (x + a / x);
            if (Math.abs(nextX - x) < Math.abs(x) * epsilon) {
                x = nextX;
                break;
            }
            x = nextX;
            if (steps > 10) break; // 安全装置
        }

        System.out.printf("Fast Newton Result: %.15f (Steps: %d)\n", x, steps);
    }
}
