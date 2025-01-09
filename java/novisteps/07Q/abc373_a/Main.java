import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long ans = 0;
		for (int i=0; i<12; i++) {
			String s = sc.next();
			if (s.length() == i+1) ans++;
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

ve
inrtfa
npccxva
djiq
lmbkktngaovl
mlfiv
fmbvcmuxuwggfq
qgmtwxmb
jii
ts
bfxrvs
eqvy
*/
