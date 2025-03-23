import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int d = sc.nextInt();
		int ans1=(a+1)*d;
		int ans2=a*(d+1);
		int ans=Math.max(ans1, ans2);
		System.out.println(ans);
	}
}
/*
31 87

101 65

3 3
*/
