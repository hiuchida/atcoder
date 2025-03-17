import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] ary=new String[n];
		int sum=0;
		for (int i=0; i<n; i++) {
			String s = sc.next();
			int c = sc.nextInt();
			ary[i]=s;
			sum+=c;
		}
		Arrays.sort(ary);
		sum%=n;
		System.out.println(ary[sum]);
	}
}
/*
3
takahashi 2
aoki 6
snuke 5

3
takahashi 2813
takahashixx 1086
takahashix 4229
*/
