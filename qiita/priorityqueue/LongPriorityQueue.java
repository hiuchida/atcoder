package util;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class LongPriorityQueue {

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    private long[] queue;

    private int size = 0;

    private final boolean descending;

    public LongPriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, false);
    }

    public LongPriorityQueue(int initialCapacity) {
        this(initialCapacity, false);
    }

    public LongPriorityQueue(boolean descending) {
        this(DEFAULT_INITIAL_CAPACITY, descending);
    }

    public LongPriorityQueue(int initialCapacity, boolean descending) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        this.queue = new long[initialCapacity];
        this.descending = descending;
    }

    private void grow() {
        int oldCapacity = queue.length;
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                         (oldCapacity + 2) :
                                         (oldCapacity >> 1));
        queue = Arrays.copyOf(queue, newCapacity);
    }

    public boolean add(long e) {
        return offer(e);
    }

    public boolean offer(long e) {
        int i = size;
        if (i >= queue.length)
            grow();
        if (size == 0) {
            queue[0] = e;
        } else {
            siftUp(i, e);
        }
        size = i + 1;
        return true;
    }

    public long peek() {
        if (size == 0) throw new NoSuchElementException("Queue is empty"); //!!
        return queue[0];
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
    }

    public long poll() {
        final long[] es = queue;
        final long result;

        if (size == 0) throw new NoSuchElementException("Queue is empty"); //!!
        result = es[0];
        final int n = --size;
        final long x = es[n];
        if (n > 0)
            siftDown(0, x);
        return result;
    }

    // --- 内部アルゴリズム ---

    private int compare(long x, long e) {
        if (descending)
            return Long.compare(e, x); // 降順: e < x なら負（xの方が優先度高）
        else
            return Long.compare(x, e); // 昇順: x < e なら負（xの方が優先度高）
    }

    private void siftUp(int k, long x) {
        siftUpUsingComparator(k, x, queue);
    }

    private void siftUpUsingComparator(int k, long x, long[] es) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            long e = es[parent];
            if (compare(x, e) >= 0)
                break;
            es[k] = e;
            k = parent;
        }
        es[k] = x;
    }

    private void siftDown(int k, long x) {
        siftDownUsingComparator(k, x, queue, size);
    }

    private void siftDownUsingComparator(int k, long x, long[] es, int n) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            long c = es[child];
            int right = child + 1;
            if (right < n && compare(c, es[right]) > 0)
                c = es[child = right];
            if (compare(x, c) <= 0)
                break;
            es[k] = c;
            k = child;
        }
        es[k] = x;
    }

}
