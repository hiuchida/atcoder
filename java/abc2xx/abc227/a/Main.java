import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int a = sc.nextInt();
		a--;
		a+=k-1;
		a%=n;
		a++;
		System.out.println(a);
	}
}
/*
3 3 2

1 100 1

3 14 2
*/
