import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final String tbl="atcoder";
		String s=sc.next();
		String t=sc.next();
		int n=s.length();
		char[] as=s.toCharArray();
		char[] at=t.toCharArray();
		int cs=0;
		int ct=0;
		for (int i=0; i<n; i++) {
			if (as[i]=='@') {
				cs++;
				as[i]='~';
			}
			if (at[i]=='@') {
				ct++;
				at[i]='~';
			}
		}
		Arrays.sort(as);
		Arrays.sort(at);
//		System.out.println(Arrays.toString(as)+" "+cs);
//		System.out.println(Arrays.toString(at)+" "+ct);
		int i=0;
		int j=0;
		while (i<n && j<n) {
//			System.out.println(i+" "+j+" "+as[i]+" "+at[j]+" "+cs+" "+ct);
			if (as[i]==at[j]) {
				i++;
				j++;
				continue;
			}
			if (as[i]<at[j]) {
				if (ct>0 && tbl.indexOf(as[i])>=0) {
					ct--;
//					System.out.println(i+" "+j+" "+as[i]+" "+at[j]+" "+cs+" "+ct);
					i++;
				} else {
					System.out.println("No");
					System.exit(0);
				}
			} else {
				if (cs>0 && tbl.indexOf(at[j])>=0) {
					cs--;
//					System.out.println(i+" "+j+" "+as[i]+" "+at[j]+" "+cs+" "+ct);
					j++;
				} else {
					System.out.println("No");
					System.exit(0);
				}
			}
		}
		while (i<n && as[i]=='~') {
			i++;
		}
		while (j<n && at[j]=='~') {
			j++;
		}
		if (i==n && j==n) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
ch@ku@ai
choku@@i

ch@kud@i
akidu@ho

aoki
@ok@

aa
bb
*/
