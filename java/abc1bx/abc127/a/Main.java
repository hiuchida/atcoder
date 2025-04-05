import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ans=0;
		if (a>=13) ans=b;
		else if (a>=6) ans=b/2;
		else ans=0;
		System.out.println(ans);
	}
}
/*
30 100

12 100

0 100
*/
