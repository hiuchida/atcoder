import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ary = new int[3];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		Arrays.sort(ary);
		int a=ary[0];
		int b=ary[1];
		int c=ary[2];
		int k = sc.nextInt();
		for (int i=0; i<k; i++) {
			c*=2;
		}
		int ans = a+b+c;
		System.out.println(ans);
	}
}
/*
5 3 11
1

3 3 4
2
*/
