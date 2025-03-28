import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		String[] ary=new String[h];
		for (int i=0; i<h; i++) {
			String s = sc.next();
			ary[i]=s;
		}
		int x1=-1;
		int y1=-1;
		int x2=-1;
		int y2=-1;
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				if (ary[i].charAt(j)=='o') {
					if (x1<0) {
						x1=j;
						y1=i;
					} else {
						x2=j;
						y2=i;
					}
				}
			}
		}
//		System.out.println(x1+" "+y1);
//		System.out.println(x2+" "+y2);
		int ans=Math.abs(x1-x2)+Math.abs(y1-y2);
		System.out.println(ans);
	}
}
/*
2 3
--o
o--

5 4
-o--
----
----
----
-o--
*/
