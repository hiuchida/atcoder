import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int ans=(x<y) ? (y-x+9)/10 : 0;
		System.out.println(ans);
	}
}
/*
80 94

1000 63

270 750
*/
