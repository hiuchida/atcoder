import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int min=n/k;
		int max=min;
		if (n%k>0) max++;
		System.out.println(max-min);
	}
}
/*
7 3

100 10

1 1
*/
