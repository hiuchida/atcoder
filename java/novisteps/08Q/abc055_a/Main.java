import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x=800*n;
		int y=200*(n/15);
		int ans=x-y;
		System.out.println(ans);
	}
}
/*
20

60
*/
