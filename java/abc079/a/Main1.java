import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary = new int[4];
		for (int i=0; i<4; i++) {
			ary[i] = n%10;
			n/=10;
		}
		Arrays.sort(ary);
		if (ary[0]==ary[2]) System.out.println("Yes");
		else if (ary[1]==ary[3]) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
1118

7777

1234
*/
