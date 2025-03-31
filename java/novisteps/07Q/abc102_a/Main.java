import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ans=n;
		if (n%2==1) ans*=2;
		System.out.println(ans);
	}
}
/*
3

10

999999999
*/
