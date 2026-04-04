package priorityqueue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

class LongPriorityQueueTest {

    @Test
    void testAscendingOrder() {
        // デフォルト（昇順/最小ヒープ）の挙動を確認
        LongPriorityQueue pq = new LongPriorityQueue();
        pq.add(100L);
        pq.add(50L);
        pq.add(200L);

        assertEquals(3, pq.size());
        assertEquals(50L, pq.peek()); // 最小値が先頭
        assertEquals(50L, pq.poll()); // 最小値から取り出される
        assertEquals(100L, pq.poll());
        assertEquals(200L, pq.poll());
        assertEquals(0, pq.size());
    }

    @Test
    void testDescendingOrder() {
        // 降順（最大ヒープ）をテスト
        LongPriorityQueue pq = new LongPriorityQueue(true);
        pq.offer(50L);
        pq.offer(200L);
        pq.offer(100L);

        assertEquals(200L, pq.peek()); // 最大値が先頭
        assertEquals(200L, pq.poll());
        assertEquals(100L, pq.poll());
        assertEquals(50L, pq.poll());
    }

    @Test
    void testEmptyQueueExceptions() {
        LongPriorityQueue pq = new LongPriorityQueue();

        // 空の状態で peek や poll を呼ぶと NoSuchElementException がスローされることを確認
        assertThrows(NoSuchElementException.class, pq::peek);
        assertThrows(NoSuchElementException.class, pq::poll);
    }

    @Test
    void testClearAndSize() {
        LongPriorityQueue pq = new LongPriorityQueue();
        pq.add(10L);
        pq.add(20L);
        assertEquals(2, pq.size());

        pq.clear(); // すべての要素を削除
        assertEquals(0, pq.size());
        assertThrows(NoSuchElementException.class, pq::poll);
    }

    @Test
    void testGrow() {
        // 初期容量を小さく設定して自動拡大（grow）をテスト
        LongPriorityQueue pq = new LongPriorityQueue(2);
        for (long i = 10; i > 0; i--) {
            pq.add(i);
        }

        assertEquals(10, pq.size());
        assertEquals(1L, pq.peek()); // 正しく最小値が維持されているか
    }

    @Test
    void testInvalidInitialCapacity() {
        // 初期容量が1未満の場合に IllegalArgumentException がスローされることを確認
        assertThrows(IllegalArgumentException.class, () -> new LongPriorityQueue(0));
    }

    @Test
    void testLargeVolume() {
        // 大量のデータを投入して整合性を確認
        LongPriorityQueue pq = new LongPriorityQueue(false);
        long count = 1000;
        for (long i = count; i > 0; i--) {
            pq.add(i);
        }

        assertEquals(count, pq.size());
        for (long i = 1; i <= count; i++) {
            assertEquals(i, pq.poll()); // 1から順に取り出されるはず
        }
    }

    @Test
    void testPairPacking() {
        // 「Pair(val, key)を1つのlongにパックする」ケースのテスト
        // val(優先度)を上位32bit、keyを下位32bitに配置して最大ヒープで管理
        LongPriorityQueue pq = new LongPriorityQueue(true);

        long maxKey = 1000000L;
        // (val * maxKey + key) の形式で投入
        pq.add(10 * maxKey + 1); // 優先度10, ID 1
        pq.add(50 * maxKey + 2); // 優先度50, ID 2
        pq.add(30 * maxKey + 3); // 優先度30, ID 3

        // 優先度(val)が高い順に取り出されることを確認
        long first = pq.poll();
        assertEquals(50, first / maxKey);
        assertEquals(2, first % maxKey);

        long second = pq.poll();
        assertEquals(30, second / maxKey);
        assertEquals(3, second % maxKey);
    }
}
