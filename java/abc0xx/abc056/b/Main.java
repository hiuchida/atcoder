import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int aw=a+w;
		int bw=b+w;
		int ans=0;
		if (aw<b) ans=b-aw;
		else if (bw<a) ans=a-bw;
		System.out.println(ans);
	}
}
/*
3 2 6

3 1 3

5 10 1
*/
