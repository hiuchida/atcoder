import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		int t = sc.nextInt();
		int ans = 0;
		for (int a=0; a<=s; a++) {
			for (int b=0; b<=s; b++) {
				for (int c=0; c<=s; c++) {
					int x=a+b+c;
					if (x>s) continue;
					int y=a*b*c;
					if (y>t) continue;
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
/*
1 0

2 5

10 10

30 100
*/
