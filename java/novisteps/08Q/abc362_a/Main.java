import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int g = sc.nextInt();
		int b = sc.nextInt();
		String s = sc.next();
		switch (s) {
		case "Red":
			r = 999;
			break;
		case "Green":
			g = 999;
			break;
		case "Blue":
			b = 999;
			break;
		}
		int ans = Math.min(Math.min(r, g), b);
		System.out.println(ans);
	}
}
/*
20 30 10
Blue

100 100 100
Red

37 39 93
Blue
*/
