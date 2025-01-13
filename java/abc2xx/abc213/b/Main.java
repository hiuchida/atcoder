import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		int m1=0;
		int m2=0;
		int n1=0;
		int n2=0;
		for (int i=0; i<ary.length; i++) {
			if (ary[i]>m1) {
				m2=m1;
				n2=n1;
				m1=ary[i];
				n1=i;
			} else if (ary[i]>m2) {
				m2=ary[i];
				n2=i;
			}
		}
		System.out.println(n2+1);
	}
}
/*
6
1 123 12345 12 1234 123456

5
3 1 4 15 9
*/
