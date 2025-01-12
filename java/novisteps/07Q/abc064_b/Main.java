import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		Arrays.sort(ary);
		int ans=ary[n-1]-ary[0];
		System.out.println(ans);
	}
}
/*
4
2 3 7 9

8
3 1 4 1 5 9 2 6
*/
