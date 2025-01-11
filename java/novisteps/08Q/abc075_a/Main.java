import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ary = new int[3];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		Arrays.sort(ary);
		if (ary[0]==ary[1]) System.out.println(ary[2]);
		else System.out.println(ary[0]);
	}
}
/*
5 7 5

1 1 7

-100 100 100
*/
