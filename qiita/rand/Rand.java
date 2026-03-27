import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class Rand {
    static class Xorshift32 {
        int state;

        Xorshift32(int seed) {
            state = seed;
        }

        double nextDouble() {
            nextLong();
            return (double) ((long) state & 0xffffffffL) * (1.0 / (double) (0xffffffffL));
        }

        int nextLong() {
            state ^= state << 13;
            state ^= state >>> 17;
            state ^= state << 5;
            return state;
        }
    }

    static class Xorshift64 {
        long state;

        Xorshift64(long seed) {
            state = seed;
        }

        double nextDouble() {
            nextLong();
            return (double) (state & Long.MAX_VALUE) * (1.0 / (double) (Long.MAX_VALUE));
        }

        long nextLong() {
            state ^= state << 13;
            state ^= state >>> 7;
            state ^= state << 17;
            return state;
        }
    }

    static void test_Random(long max) {
        System.out.println("Random");
        long start = System.currentTimeMillis();
        Random rand = new Random();
        rand.setSeed(1);
        for (int i = 0; i < max; i++) {
            double v = rand.nextDouble();
        }
        long end = System.currentTimeMillis();
        System.out.println("" + (end - start));
    }

    static void test_ThreadLocalRandom(long max) {
        System.out.println("ThreadLocalRandom");
        long start = System.currentTimeMillis();
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        //rand.setSeed(1); // UnsupportedOperationException
        for (int i = 0; i < max; i++) {
            double v = rand.nextDouble();
        }
        long end = System.currentTimeMillis();
        System.out.println("" + (end - start));
    }

    static void test_SplittableRandom(long max) {
        System.out.println("SplittableRandom");
        long start = System.currentTimeMillis();
        SplittableRandom rand = new SplittableRandom(1);
        //rand.setSeed(1); // compile error
        for (int i = 0; i < max; i++) {
            double v = rand.nextDouble();
        }
        long end = System.currentTimeMillis();
        System.out.println("" + (end - start));
    }

    static void test_Xorshift32(long max) {
        System.out.println("Xorshift32");
        long start = System.currentTimeMillis();
        Xorshift32 xs = new Xorshift32(1);
        for (int i = 0; i < max; i++) {
            double v = xs.nextDouble();
        }
        long end = System.currentTimeMillis();
        System.out.println("" + (end - start));
    }

    static void test_Xorshift64(long max) {
        System.out.println("Xorshift64");
        long start = System.currentTimeMillis();
        Xorshift64 xs = new Xorshift64(1);
        for (int i = 0; i < max; i++) {
            double v = xs.nextDouble();
        }
        long end = System.currentTimeMillis();
        System.out.println("" + (end - start));
    }

    static void test_RandomGenerator(String name, long max) throws NoSuchAlgorithmException {
        System.out.println(name);
        long start = System.currentTimeMillis();
        RandomGenerator g = RandomGeneratorFactory.of(name).create();
        for (int i = 0; i < max; i++) {
            double v = g.nextDouble();
        }
        long end = System.currentTimeMillis();
        System.out.println("" + (end - start));
    }

    static void test_SecureRandom(String name, long max) throws NoSuchAlgorithmException {
        System.out.println(name);
        long start = System.currentTimeMillis();
        Random rand = SecureRandom.getInstance(name);
        for (int i = 0; i < max; i++) {
            double v = rand.nextDouble();
        }
        long end = System.currentTimeMillis();
        System.out.println("" + (end - start));
    }

    static void batch(long base) throws NoSuchAlgorithmException {
        String[] name_SecureRandom = {
            //"NativePRNG",
            //"NativePRNGBlocking",
            //"NativePRNGNonBlocking",
            //"PKCS11",
            "DRBG",
            "SHA1PRNG",
            "Windows-PRNG",
        };
        String[] name_RandomGenerator = {
            "L32X64MixRandom",
            "L64X128MixRandom",
            "L64X128StarStarRandom",
            "L64X256MixRandom",
            "L64X1024MixRandom",
            "L128X128MixRandom",
            "L128X256MixRandom",
            "L128X1024MixRandom",
            "Xoroshiro128PlusPlus",
            "Xoshiro256PlusPlus",
        };
        test_Random(base);
        test_RandomGenerator("Random", base);
        test_Xorshift32(base);
        test_Xorshift64(base);
        test_ThreadLocalRandom(base);
        test_SplittableRandom(base);
        test_RandomGenerator("SplittableRandom", base);
        for (String name : name_RandomGenerator) {
            test_RandomGenerator(name, base);
        }
        test_RandomGenerator("SecureRandom", base);
        for (String name : name_SecureRandom) {
            test_SecureRandom(name, base);
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        long base = 1000 * 1000;
        batch(base);
        //batch(10*base);
        //batch(100*base);
    }
}
