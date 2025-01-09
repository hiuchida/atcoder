import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ary = new int[3];
		int i=0;
		ary[i++] = sc.nextInt();
		ary[i++] = sc.nextInt();
		ary[i++] = sc.nextInt();
		Arrays.sort(ary);
		if (ary[0] == 5 && ary[1] == 5 && ary[2] == 7) System.out.println("YES");
		else System.out.println("NO");
	}
}
/*
5 5 7

7 7 5
*/
