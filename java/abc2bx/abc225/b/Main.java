import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] aa = new int[n];
		int[] ab = new int[n];
		for (int i=0; i<n-1; i++) {
			aa[i] = sc.nextInt();
			ab[i] = sc.nextInt();
		}
		int a1=aa[0];
		int b1=ab[0];
		for (int i=1; i<n-1; i++) {
			if (aa[i]==a1 || ab[i]==a1) {
				b1=-1;
			} else if (aa[i]==b1 || ab[i]==b1) {
				a1=-1;
			} else {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
}
/*
5
1 4
2 4
3 4
4 5

4
2 4
1 4
2 3

10
9 10
3 10
4 10
8 10
1 10
2 10
7 10
6 10
5 10
*/
