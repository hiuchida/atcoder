import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ary = new int[4];
		for (int i=0; i<4; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		Arrays.sort(ary);
		if (ary[0] == ary[1] && ary[2] == ary[3] && ary[1] != ary[2])
			System.out.println("Yes");
		else if (ary[0] == ary[2] && ary[2] != ary[3])
			System.out.println("Yes");
		else if (ary[0] != ary[1] && ary[1] == ary[2] && ary[2] == ary[3])
			System.out.println("Yes");
		else
			System.out.println("No");
	}
}
