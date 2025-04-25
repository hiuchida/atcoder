import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int a=sc.nextInt();
		int b=sc.nextInt();
		int ab=a+b;
		int[] ary=new int[n];
		for (int i=1; i<=n; i++) {
			int c=sc.nextInt();
			if (ab==c) {
				System.out.println(i);
				System.exit(0);
			}
		}
	}
}
/*
3 125 175
200 300 400

1 1 1
2

5 123 456
135 246 357 468 579
*/
