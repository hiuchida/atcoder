import java.util.*;
public class Main {
	static int n;
	static char[] ary;
	static boolean isAbc(int i) {
		if (i-1 < 0 || n-1 < i+1) return false;
		if (ary[i-1] == 'A' && ary[i] == 'B' && ary[i+1] == 'C') return true;
		return false;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int q = sc.nextInt();
		String s = sc.next();
		ary = s.toCharArray();
		int hit = 0;
		for (int i=1; i<ary.length-1; i++) {
			if (isAbc(i)) hit++;
		}
//		System.out.println(hit);
		for (int i=0; i<q; i++) {
//			System.out.println(Arrays.toString(ary));
			int x = sc.nextInt() - 1;
			String c = sc.next();
			int d=0;
			if (isAbc(x-1)) d--;
			if (isAbc(x)) d--;
			if (isAbc(x+1)) d--;
			ary[x] = c.charAt(0);
			if (isAbc(x-1)) d++;
			if (isAbc(x)) d++;
			if (isAbc(x+1)) d++;
			hit += d;
			System.out.println(hit);
		}
//		System.out.println(Arrays.toString(ary));
	}
}
