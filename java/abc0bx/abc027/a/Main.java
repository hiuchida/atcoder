import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int N=3;
		int[] ary=new int[N];
		for (int i=0; i<N; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
		if (ary[0]==ary[1]) System.out.println(ary[2]);
		else System.out.println(ary[0]);
	}
}
/*
1 1 2

4 3 4

5 5 5
*/
