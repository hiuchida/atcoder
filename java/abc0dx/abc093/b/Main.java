import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int k=sc.nextInt();
		int aa=a+k-1;
		int bb=b-k+1;
		if (aa<bb) {
			for (int i=a; i<=aa; i++) {
				System.out.println(i);
			}
			for (int i=bb; i<=b; i++) {
				System.out.println(i);
			}
		} else {
			for (int i=a; i<=b; i++) {
				System.out.println(i);
			}
		}
	}
}
/*
3 8 2

4 8 3

2 9 100
*/
