import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int s=sc.nextInt();
		int d=sc.nextInt();
		for (int i=0; i<n; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			if (s<=x || d>=y) continue;
			System.out.println("Yes");
			System.exit(0);
		}
		System.out.println("No");
	}
}
/*
4 9 9
5 5
15 5
5 15
15 15

3 691 273
691 997
593 273
691 273

7 100 100
10 11
12 67
192 79
154 197
142 158
20 25
17 108
*/
