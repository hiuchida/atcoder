import java.util.*;
public class Main {
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[9][9];
		Set<Integer> setx = new TreeSet<>();
		Set<Integer> sety = new TreeSet<>();
		for (int y = 1; y <= 8; y++) {
			String s = sc.nextLine();
			for (int x = 1; x <= 8; x++) {
				if (s.charAt(x - 1) == '#') {
					map[y][x] = 1;
					sety.add(y);
					setx.add(x);
				}
			}
		}
//		for (int y = 0; y < 9; y++) {
//			for (int x = 0; x < 9; x++) {
//				System.out.print(map[y][x] > 0 ? '#':'.');
//			}
//			System.out.println();
//		}
//		System.out.println(sety);
//		System.out.println(setx);
		int ans = 0;
		for (int y = 1; y <= 8; y++) {
			for (int x = 1; x <= 8; x++) {
				if (!sety.contains(y) && !setx.contains(x)) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
