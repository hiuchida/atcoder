import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=3;
		int[] ary=new int[N];
		for (int i=0; i<N; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
		if (ary[0]==ary[1] && ary[1]==ary[2]) System.out.println("No");
		else if (ary[0]!=ary[1] && ary[1]!=ary[2]) System.out.println("No");
		else System.out.println("Yes");
	}
}
/*
5 7 5

4 4 4

4 9 6

3 3 4
*/
