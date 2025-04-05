import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ans=0;
		for (int i=1; i<=2; i++) {
			if (a>=b) {
				ans+=a;
				a--;
			} else {
				ans+=b;
				b--;
			}
		}
		System.out.println(ans);
	}
}
/*
5 3

3 4

6 6
*/
