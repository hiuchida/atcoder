import java.util.*;
public class Main {
	static final int[] DY = { 1, 0,-1, 0 }; //N,E,S,W
	static final int[] DX = { 0, 1, 0,-1 }; //N,E,S,W
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String t = sc.next();
		int x=0;
		int y=0;
		int d=1;
		for (int i=0; i<t.length(); i++) {
			if (t.charAt(i)=='S') {
				x+=DX[d];
				y+=DY[d];
			} else {
				d++;
				d%=4;
			}
		}
		System.out.println(x+" "+y);
	}
}
/*
4
SSRS

20
SRSRSSRSSSRSRRRRRSRR
*/
