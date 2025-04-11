import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int aa=calc(k, a);
		int bb=calc(k, b);
		int ans = aa*bb;
		System.out.println(ans);
	}
	static int calc(int k, int x) { //abc220_b: k進表現のxを十進数に変換する
		int ans=0;
		int w=1;
		while (x>0) {
			int v=x%10;
			ans+=v*w;
			w*=k;
			x/=10;
		}
		return ans;
	}
}
/*
2
1011 10100

7
123 456
*/
