package priorityqueue;

import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueComparison {
    public static void main(String[] args) {
        // 1. 正当性の検証 (同一の乱数列を使用)
        testConsistency(100_000);

        // 2. 実行速度の比較 (100万件)
        benchmark(1_000_000);

        // 3. 実行速度の比較 (1000万件)
        benchmark(10_000_000);
    }

    /**
     * 同じ乱数を使用して、取り出される順序が標準のPriorityQueueと一致するか検証する
     */
    private static void testConsistency(int n) {
        PriorityQueue<Integer> standardPQ = new PriorityQueue<>();
        IntPriorityQueue customPQ = new IntPriorityQueue();
        long seed = System.currentTimeMillis();
        Random randAdd = new Random(seed);
        
        System.out.println("--- testConsistency (" + n + ") ---");
        
        for (int i = 0; i < n; i++) {
            int val = randAdd.nextInt();
            standardPQ.add(val);
            customPQ.add(val);
        }

        boolean match = true;
        for (int i = 0; i < n; i++) {
            int val1 = standardPQ.poll();
            int val2 = customPQ.poll();
            if (val1 != val2) {
                match = false;
                break;
            }
        }
        System.out.println("result: " + (match ? "ok" : "ng"));
    }

    /**
     * 大量データでの実行速度を比較する
     */
    private static void benchmark(int n) {
        int[] data = new int[n];
        Random rand = new Random(42);
        for (int i = 0; i < n; i++) data[i] = rand.nextInt();

        System.out.println("\n--- benchmark (" + n + ") ---");

        // 標準 PriorityQueue (Integer オブジェクトを使用)
        long start1 = System.currentTimeMillis();
        PriorityQueue<Integer> standardPQ = new PriorityQueue<>(n);
        for (int v : data) standardPQ.add(v);
        while (!standardPQ.isEmpty()) standardPQ.poll();
        long end1 = System.currentTimeMillis();
        long durationStandard = end1 - start1; // 1回目の所要時間を保存

        // 自作 IntPriorityQueue (プリミティブ int を直接操作)
        long start2 = System.currentTimeMillis();
        IntPriorityQueue customPQ = new IntPriorityQueue(n);
        for (int v : data) customPQ.add(v);
        while (customPQ.size() > 0) customPQ.poll();
        long end2 = System.currentTimeMillis();
        long durationCustom = end2 - start2; // 2回目の所要時間を保存
        
        System.out.println("java.util.PriorityQueue: " + durationStandard + " ms");
        System.out.println("IntPriorityQueue:        " + durationCustom + " ms");
        double improvement = (double)durationStandard / durationCustom;
        System.out.printf("improvement: %.2f \n", improvement);
    }
}
