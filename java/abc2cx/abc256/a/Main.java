import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long ans=1;
		while (n>0) {
			ans*=2;
			n--;
		}
		System.out.println(ans);
	}
}
/*
3

30
*/
