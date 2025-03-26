import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long x = sc.nextLong();
		String s = sc.next();
		char[] ary=s.toCharArray();
		int cnt=0;
		for (int i=n-1; i>=0; i--) {
			if (ary[i]=='U') {
				cnt++;
			} else {
				if (cnt>0) {
					ary[i]=' ';
					cnt--;
					for (int j=i+1; j<n; j++) { //DEBUG
						if (ary[j]=='U') {
							ary[j]='u';
							break;
						}
					}
				}
			}
		}
//		System.out.println(Arrays.toString(ary)+" "+cnt);
		for (int i=0; i<cnt; i++) {
			x/=2;
		}
		for (int i=0; i<n; i++) {
			switch (ary[i]) {
//			case 'U':
//				break;
			case 'L':
				x=2*x;
				break;
			case 'R':
				x=2*x+1;
				break;
			}
		}
		System.out.println(x);
	}
}
/*
3 2
URL

4 500000000000000000
RRUU

30 123456789
LRULURLURLULULRURRLRULRRRUURRU
*/
/*
3 2
RUL
*/
