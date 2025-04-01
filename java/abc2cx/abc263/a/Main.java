import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 5;
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
		if (ary[0]==ary[1] && ary[1]==ary[2]) {
			if (ary[3]==ary[4]) System.out.println("Yes");
			else System.out.println("No");
		} else if (ary[2]==ary[3] && ary[3]==ary[4]) {
			if (ary[0]==ary[1]) System.out.println("Yes");
			else System.out.println("No");
		} else {
			System.out.println("No");
		}
	}
}
/*
1 2 1 2 1

12 12 11 1 2
*/
