import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int ans=ary[0];
		for (int i=1; i<n; i++) {
			if (ans<ary[i]) {
				ans=ary[i];
			} else {
				break;
			}
		}
		System.out.println(ans);
	}
}
/*
5
1 5 10 4 2

3
100 1000 100000

4
27 1828 1828 9242
*/
