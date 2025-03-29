import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		for (int y=0; y<n; y++) {
			char[] ary=new char[n*b];
			int idx=0;
			for (int x=0; x<n; x++) {
				boolean bBlack=(x+y)%2==1;
				for (int xx=0; xx<b; xx++) {
					ary[idx++]=bBlack ? '#':'.';
				}
			}
			String s=new String(ary);
			for (int yy=0; yy<a; yy++) {
				System.out.println(s);
			}
		}
	}
}
/*
4 3 2

5 1 5

4 4 1

1 4 4
*/
