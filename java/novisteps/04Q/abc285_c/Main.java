import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		long ans=calc(26, s);
		System.out.println(ans);
	}
	static long calc(int k, String s) { //abc285_c: A-Zのk進表現のsを十進数に変換する
		long ans=0;
		for (int i=0; i<s.length(); i++) {
			long v=s.charAt(i)-'A'+1;
			ans*=k;
			ans+=v;
		}
		return ans;
	}
}
/*
AB

C

BRUTMHYHIIZP
*/
