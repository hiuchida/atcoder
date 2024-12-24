import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int d = sc.nextInt();
			int a = sc.nextInt();
			if (d == 1) {
				if (1600 <= r && r <= 2799) r += a;
			} else {
				if (1200 <= r && r <= 2399) r += a;
			}
		}
		System.out.println(r);
	}
}
