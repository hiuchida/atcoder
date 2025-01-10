import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		if (group(x)==group(y)) System.out.println("Yes");
		else System.out.println("No");
	}
	static int group(int a) {
		switch (a) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			return 28;
		}
		return 0;
	}
}
/*
1 3

2 4
*/
