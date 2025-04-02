import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=10;
		String[] ary=new String[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.next();
		}
		int lt=0;
		int tp=0;
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (ary[y].charAt(x)=='#') {
					lt=x+1;
					tp=y+1;
					y=n;
					x=n;
				}
			}
		}
		int rt=n;
		int bm=n;
		for (int y=tp; y<n; y++) {
			if (ary[y].charAt(lt)=='.') {
				bm=y;
				break;
			}
		}
		for (int x=lt; x<n; x++) {
			if (ary[tp].charAt(x)=='.') {
				rt=x;
				break;
			}
		}
		System.out.println(tp+" "+bm);
		System.out.println(lt+" "+rt);
	}
}
/*
..........
..........
..........
..........
...######.
...######.
...######.
...######.
..........
..........

..........
..#.......
..........
..........
..........
..........
..........
..........
..........
..........

##########
##########
##########
##########
##########
##########
##########
##########
##########
##########
*/
