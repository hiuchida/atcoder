import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		String[] aa=new String[n];
		String[] ab=new String[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.next();
		}
		for (int i=0; i<n; i++) {
			ab[i]=sc.next();
		}
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (aa[y].charAt(x)!=ab[y].charAt(x)) {
					System.out.println((y+1)+" "+(x+1));
					System.exit(0);
				}
			}
		}
	}
}
/*
3
abc
def
ghi
abc
bef
ghi

1
f
q

10
eixfumagit
vtophbepfe
pxbfgsqcug
ugpugtsxzq
bvfhxyehfk
uqyfwtmglr
jaitenfqiq
acwvufpfvv
jhaddglpva
aacxsyqvoj
eixfumagit
vtophbepfe
pxbfgsqcug
ugpugtsxzq
bvfhxyehok
uqyfwtmglr
jaitenfqiq
acwvufpfvv
jhaddglpva
aacxsyqvoj
*/
