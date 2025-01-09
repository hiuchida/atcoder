import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		long ans = 0;
		for (int i=1; i<10; i++) {
			for (int j=1; j<10; j++) {
				if (i*j!=x) ans+=i*j;
			}
		}
		System.out.println(ans);
	}
}
/*
1

11

24
*/
