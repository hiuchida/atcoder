import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long sum = n*(n+1)/2; //abc181_b,abc369_c: 初項a、末項bの等差数列の和
		System.out.println(sum);
	}
}
/*
3

10

1
*/
