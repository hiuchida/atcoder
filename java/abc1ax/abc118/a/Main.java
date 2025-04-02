import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ans=0;
		if (b%a==0) ans=a+b;
		else ans=b-a;
		System.out.println(ans);
	}
}
/*
4 12

8 20

1 1
*/
