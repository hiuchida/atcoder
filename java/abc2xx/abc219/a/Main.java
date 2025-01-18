import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ans=0;
		if (n<40) ans=40-n;
		else if (n<70) ans=70-n;
		else if (n<90) ans=90-n;
		else {
			System.out.println("expert");
			System.exit(0);
		}
		System.out.println(ans);
	}
}
/*
56

32

0

100
*/
