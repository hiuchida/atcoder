import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c=a%b;
		int ans=(c>0) ? b-c : 0;
		System.out.println(ans);
	}
}
/*
7
3

5
5

1
100

25
12
*/
