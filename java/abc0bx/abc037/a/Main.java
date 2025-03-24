import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int ans=0;
		for (int i=0; true; i++) {
			if (a*i>c) break;
			int cc=c-a*i;
			int j=cc/b;
			ans=Math.max(ans, i+j);
		}
		System.out.println(ans);
	}
}
/*
3 5 6

8 6 20
*/
