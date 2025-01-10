import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ary = new int[3];
		for (int i=0; i<3; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		Arrays.sort(ary);
		int ans=ary[0]+ary[1];
		System.out.println(ans);
	}
}
/*
700 600 780

10000 10000 10000
*/
