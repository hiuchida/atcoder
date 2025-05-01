import java.util.*;
public class Main {
	static final int[] DY = { -1, 1, 0, 0, -1,-1, 1, 1, }; //U,D,L,R, UL,UR,DL,DR
	static final int[] DX = {  0, 0,-1, 1, -1, 1,-1, 1, }; //U,D,L,R, UL,UR,DL,DR
	static int h;
	static int w;
	static String[] ary;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		h=sc.nextInt();
		w=sc.nextInt();
		ary=new String[h];
		for (int y=0; y<h; y++) {
			ary[y]=sc.next();
		}
		for (int y=0; y<h; y++) {
			for (int x=0; x<w; x++) {
				check(y, x);
			}
		}
	}
	static void check(int y, int x) {
		final String tbl="snuke";
		char ch=ary[y].charAt(x);
		if (ch!='s') return;
		for (int d=0; d<DY.length; d++) {
			int i;
			for (i=1; i<tbl.length(); i++) {
				int ey=y+DY[d]*i;
				int ex=x+DX[d]*i;
				if (ey<0 || h-1<ey || ex<0 || w-1<ex) break;
				ch=ary[ey].charAt(ex);
				if (ch!=tbl.charAt(i)) break;
			}
			if (i==tbl.length()) {
				for (i=0; i<tbl.length(); i++) {
					int ey=y+DY[d]*i+1;
					int ex=x+DX[d]*i+1;
					System.out.println(ey+" "+ex);
				}
				System.exit(0);
			}
		}
	}
}
/*
6 6
vgxgpu
amkxks
zhkbpp
hykink
esnuke
zplvfj

5 5
ezzzz
zkzzz
ezuzs
zzznz
zzzzs

10 10
kseeusenuk
usesenesnn
kskekeeses
nesnusnkkn
snenuuenke
kukknkeuss
neunnennue
sknuessuku
nksneekknk
neeeuknenk
*/
