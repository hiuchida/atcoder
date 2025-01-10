import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ans=x-a;
		ans%=b;
		System.out.println(ans);
	}
}
/*
1234
150
100

1000
108
108

579
123
456

7477
549
593
*/
