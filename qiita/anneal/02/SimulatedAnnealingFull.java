import java.util.Random;

public class SimulatedAnnealingFull {

    // 探索したい関数 (例: 複数の山がある複雑な波)
    public static double f(double x) {
        // xが範囲外に出すぎないよう制約（ペナルティ）を入れるのが一般的
        if (x < 0 || x > 100) return -999; 
        return Math.sin(x) + Math.cos(x * 0.5) + Math.sin(x * 2.0);
    }

    public static void main(String[] args) {
        Random rand = new Random();

        // --- 設定パラメータ ---
        double currentX = rand.nextDouble() * 100; // 初期位置は0-100のどこか
        double temp = 100.0;                       // 初期温度
        double coolingRate = 0.9999;               // 非常にゆっくり冷やす
        double bestX = currentX;

        System.out.printf("開始地点: x = %.4f, f(x) = %.4f%n", currentX, f(currentX));

        // --- メインループ ---
        int iterations = 0;
        while (temp > 0.0001) {
            // 【ポイント】温度が高いほど歩幅(dx)を大きくする
            // temp=100のときは最大±10、temp=0.1のときは最大±0.01動く
            double moveRange = temp * 0.1; 
            double dx = (rand.nextDouble() * 2 - 1) * moveRange;
            double nextX = currentX + dx;

            double currentEnergy = f(currentX);
            double nextEnergy = f(nextX);

            // 判定
            if (nextEnergy > currentEnergy) {
                // 改善されたら移動
                currentX = nextX;
            } else {
                // 悪化しても確率的に移動（温度が高いほど移動しやすい）
                double p = Math.exp((nextEnergy - currentEnergy) / temp);
                if (p > rand.nextDouble()) {
                    currentX = nextX;
                }
            }

            // これまでで最高の地点を保存
            boolean bBest = false;
            if (f(currentX) > f(bestX)) {
                bestX = currentX;
                bBest = true;
            }

            // 冷却
            temp *= coolingRate;
            iterations++;
            if (bBest || iterations%1000==0) System.out.printf("i=%6d t=%8.5f b=%7.4f c=%7.4f f(b)=%7.4f f(c)=%7.4f %c\n", 
            		iterations, temp, bestX, currentX, f(bestX), f(currentX), bBest ? '*' : ' ');
        }

        System.out.println("------------------------------------");
        System.out.printf("探索回数: %d 回%n", iterations);
        System.out.printf("最終結果: x = %.4f, f(x) = %.4f%n", bestX, f(bestX));
    }
}
