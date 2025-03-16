import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int l = sc.nextInt();
		int r = sc.nextInt();
		for (int i=1; i<l; i++) {
			System.out.print(i+" ");
		}
		for (int i=r; i>=l; i--) {
			System.out.print(i+" ");
		}
		for (int i=r+1; i<=n; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
}
/*
5 2 3

7 1 1

10 1 10
*/
