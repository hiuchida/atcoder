import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		int t=sc.nextInt();
		int x=0;
		int y=0;
		int q=0;
		for (char ch : ary) {
			switch (ch) {
			case 'L':
				x--;
				break;
			case 'R':
				x++;
				break;
			case 'U':
				y++;
				break;
			case 'D':
				y--;
				break;
			case '?':
				q++;
				break;
			}
		}
		int d=calc(0, 0, x, y);
		if (t==1) {
			int ans=d+q;
			System.out.println(ans);
		} else {
			int ans=0;
			if (d>q) ans=d-q;
			else {
				q-=d;
				ans=q%2;
			}
			System.out.println(ans);
		}
	}
	static int calc(int x1, int y1, int x2, int y2) { //abc035_b,abc057_b,abc295_b: x1,y1からx2,y2までのマンハッタン距離
		int dx=x1-x2;
		int dy=y1-y2;
		return Math.abs(dx)+Math.abs(dy);
	}
}
/*
UL?
1

UD?
1

UUUU?DDR?LLLL
1

UULL?
2
*/
/*
?
2

??
2

U??
2
*/
