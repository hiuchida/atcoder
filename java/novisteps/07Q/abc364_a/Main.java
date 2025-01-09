import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int c = 0;
		for (int i=0; i<n; i++) {
			if (c==2) {
				System.out.println("No");
				System.exit(0);
			}
			String s = sc.next();
			if ("sweet".equals(s)) c++;
			else c=0;
		}
		System.out.println("Yes");
	}
}
/*
5
salty
sweet
salty
salty
sweet

4
sweet
salty
sweet
sweet

6
salty
sweet
sweet
salty
sweet
sweet
*/
