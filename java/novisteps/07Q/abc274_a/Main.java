import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		if (a==b) System.out.println("1.000");
		else {
			int x=10000*b/a;
			int ans=x/10;
			if (x%10>=5) ans++;
			System.out.println("0."+String.format("%03d", ans));
		}
	}
}
/*
7 4

7 3

2 1

10 10

1 0
*/
