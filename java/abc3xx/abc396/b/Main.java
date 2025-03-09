import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt();
		int[] ary=new int[201];
		int sp=100;
		for (int i=0; i<q; i++) {
			int c=sc.nextInt();
			if (c==1) {
				int x=sc.nextInt();
				ary[sp++]=x;
			} else {
				System.out.println(ary[--sp]);
			}
		}
	}
}
/*
6
2
1 4
1 3
2
2
2

5
2
2
2
2
2
*/
