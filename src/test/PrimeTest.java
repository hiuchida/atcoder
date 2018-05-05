package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrimeTest {
	public static void main(String[] args) {
		new PrimeTest().execute();
	}

	void execute() {
		long st = System.currentTimeMillis();
		loop();
		long ed = System.currentTimeMillis();
		System.out.println((ed - st) + "ms");
		for (int i = 0; i < prime.length; i++) {
			if (prime[i] != l.get(i))
				throw new RuntimeException("NG");
		}
		for (int i = 0; i <= 10000 * 1000; i++) {
			if (isPrime(i) != isPrime4(i))
				throw new RuntimeException("NG");
		}
		System.out.println("OK");
	}

	final int max = 1000 * 1000;
	List<Integer> l = new ArrayList<>(max);
	final int[] prime = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,
			97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
			211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331,
			337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457,
			461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599,
			601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733,
			739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877,
			881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997 };
	final Set<Integer> primeSet = new HashSet<>();

	PrimeTest() {
		for (int p : prime)
			primeSet.add(p);
	}

	void loop() {
		for (int i = 1; i < max; i++) {
			if (isPrime(i))
				l.add(i);
		}
	}

	final Prime primeObj = new Prime();

	// 1M:65ms
	// 10M:1563ms
	boolean isPrime(int num) {
		return primeObj.isPrime(num);
	}

	// 1M:64ms
	// 10M:1577ms
	boolean isPrime6(int num) {
		if (num <= 1000)
			return primeSet.contains(num);
		for (int i : prime) {
			if (num % i == 0)
				return false;
		}
		int sqrtNum = (int) Math.ceil(Math.sqrt(num));
		for (int i = 1001; i <= sqrtNum; i += 2) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	// 1M:103ms
	// 10M:2248ms
	boolean isPrime5(int num) {
		if (num <= 1000)
			return primeSet.contains(num);
		else if (num % 2 == 0)
			return false;
		int sqrtNum = (int) Math.ceil(Math.sqrt(num));
		for (int i = 3; i <= sqrtNum; i += 2) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	// 100K:13ms
	// 1M:99ms
	// 10M:2186ms
	boolean isPrime4(int num) {
		if (num < 2)
			return false;
		else if (num == 2 || num == 3)
			return true;
		else if (num % 2 == 0 || num % 3 == 0)
			return false;
		int sqrtNum = (int) Math.ceil(Math.sqrt(num));
		for (int i = 3; i <= sqrtNum; i += 2) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	// 100K:18ms
	// 1M:104ms
	// 10M:2302ms
	boolean isPrime3(int num) {
		if (num < 2)
			return false;
		else if (num == 2)
			return true;
		else if (num % 2 == 0)
			return false;
		int sqrtNum = (int) Math.ceil(Math.sqrt(num));
		for (int i = 3; i <= sqrtNum; i += 2) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	// 100K:566ms
	boolean isPrime2(int num) {
		if (num < 2)
			return false;
		else if (num == 2)
			return true;
		else if (num % 2 == 0)
			return false;
		for (int i = 3; i < num; i += 2) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	// 100K:1140ms
	boolean isPrime1(int num) {
		if (num < 2)
			return false;
		for (int i = 2; i < num; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

}
