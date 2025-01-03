import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long bb = 5;
		for (int b=1; b<38; b++,bb*=5) {
			long aa = 3;
			for (int a=1; true; a++,aa*=3) {
				if (aa+bb>n) break;
				if (aa+bb==n) {
					System.out.println(a + " " + b);
					System.exit(0);
				}
			}
		}
		System.out.println("-1");
	}
}
