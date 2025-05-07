import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=12;
		String[] ary=new String[N];
		for (int i=0; i<N; i++) {
			ary[i]=sc.next();
		}
		int ans=0;
		for (int i=0; i<N; i++) {
			if (ary[i].indexOf("r")>=0) ans++;
		}
		System.out.println(ans);
	}
}
/*
january
february
march
april
may
june
july
august
september
october
november
december

rrrrrrrrrr
srrrrrrrrr
rsr
ssr
rrs
srsrrrrrr
rssrrrrrr
sss
rrr
srr
rsrrrrrrrr
ssrrrrrrrr
*/
