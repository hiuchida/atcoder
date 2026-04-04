package priorityqueue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

class IntPriorityQueueTest {

    @Test
    void testAscendingOrder() {
        // デフォルトの昇順（最小ヒープ）をテスト
        IntPriorityQueue pq = new IntPriorityQueue();
        pq.add(30);
        pq.add(10);
        pq.add(20);

        assertEquals(3, pq.size());
        assertEquals(10, pq.peek()); // 最小値が先頭
        assertEquals(10, pq.poll()); // 最小値から取り出される
        assertEquals(20, pq.poll());
        assertEquals(30, pq.poll());
        assertEquals(0, pq.size());
    }

    @Test
    void testDescendingOrder() {
        // 降順（最大ヒープ）をテスト
        IntPriorityQueue pq = new IntPriorityQueue(true);
        pq.offer(10);
        pq.offer(30);
        pq.offer(20);

        assertEquals(30, pq.peek()); // 最大値が先頭
        assertEquals(30, pq.poll());
        assertEquals(20, pq.poll());
        assertEquals(10, pq.poll());
    }

    @Test
    void testEmptyQueueExceptions() {
        IntPriorityQueue pq = new IntPriorityQueue();

        // 空の状態で peek や poll を呼ぶと NoSuchElementException がスローされることを確認
        assertThrows(NoSuchElementException.class, () -> pq.peek());
        assertThrows(NoSuchElementException.class, () -> pq.poll());
    }

    @Test
    void testClearAndSize() {
        IntPriorityQueue pq = new IntPriorityQueue();
        pq.add(1);
        pq.add(2);
        assertEquals(2, pq.size());

        pq.clear(); // すべての要素を削除
        assertEquals(0, pq.size());
        assertThrows(NoSuchElementException.class, () -> pq.poll());
    }

    @Test
    void testGrow() {
        // 初期容量を小さく設定して自動拡大（grow）をテスト
        IntPriorityQueue pq = new IntPriorityQueue(2);
        pq.add(5);
        pq.add(4);
        pq.add(3); // ここで容量が拡大される
        pq.add(2);
        pq.add(1);

        assertEquals(5, pq.size());
        assertEquals(1, pq.poll()); // 正しく最小値が維持されているか
    }

    @Test
    void testInvalidInitialCapacity() {
        // 初期容量が1未満の場合に IllegalArgumentException がスローされることを確認
        assertThrows(IllegalArgumentException.class, () -> {
            new IntPriorityQueue(0);
        });
    }

    @Test
    void testLargeVolume() {
        // 大量のデータを投入して整合性を確認
        IntPriorityQueue pq = new IntPriorityQueue(false);
        int count = 1000;
        for (int i = count; i > 0; i--) {
            pq.add(i);
        }

        assertEquals(count, pq.size());
        for (int i = 1; i <= count; i++) {
            assertEquals(i, pq.poll()); // 1から順に取り出されるはず
        }
    }
}
