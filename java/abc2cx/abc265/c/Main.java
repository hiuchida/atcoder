import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		String[] ary=new String[h];
		for (int i=0; i<h; i++) {
			ary[i]=sc.next();
		}
		int y=1;
		int x=1;
		boolean bdone=false;
		boolean[][] flag=new boolean[h][w];
		while (!bdone) {
			if (flag[y-1][x-1]) {
				System.out.println(-1);
				System.exit(0);
			}
			flag[y-1][x-1]=true;
			char ch=ary[y-1].charAt(x-1);
			switch (ch) {
			case 'U':
				if (y==1) bdone=true;
				else y--;
				break;
			case 'D':
				if (y==h) bdone=true;
				else y++;
				break;
			case 'L':
				if (x==1) bdone=true;
				else x--;
				break;
			case 'R':
				if (x==w) bdone=true;
				else x++;
				break;
			}
		}
		System.out.println(y+" "+x);
	}
}
/*
2 3
RDU
LRU

2 3
RRD
ULL

9 44
RRDDDDRRRDDDRRRRRRDDDRDDDDRDDRDDDDDDRRDRRRRR
RRRDLRDRDLLLLRDRRLLLDDRDLLLRDDDLLLDRRLLLLLDD
DRDLRLDRDLRDRLDRLRDDLDDLRDRLDRLDDRLRRLRRRDRR
DDLRRDLDDLDDRLDDLDRDDRDDDDRLRRLRDDRRRLDRDRDD
RDLRRDLRDLLLLRRDLRDRRDRRRDLRDDLLLLDDDLLLLRDR
RDLLLLLRDLRDRLDDLDDRDRRDRLDRRRLDDDLDDDRDDLDR
RDLRRDLDDLRDRLRDLDDDLDDRLDRDRDLDRDLDDLRRDLRR
RDLDRRLDRLLLLDRDRLLLRDDLLLLLRDRLLLRRRRLLLDDR
RRRRDRDDRRRDDRDDDRRRDRDRDRDRRRRRRDDDRDDDDRRR
*/
