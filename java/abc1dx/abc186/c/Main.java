import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long ans=0;
		for (int i=1; i<=n; i++) {
			if (check10(i)) continue;
			if (check8(i)) continue;
			ans++;
		}
		System.out.println(ans);
	}
	static boolean check10(int i) {
		while (i>0) {
			if (i%10==7) return true;
			i/=10;
		}
		return false;
	}
	static boolean check8(int i) {
		while (i>0) {
			if (i%8==7) return true;
			i/=8;
		}
		return false;
	}
}
/*
20

100000
*/
