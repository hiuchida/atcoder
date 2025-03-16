import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ary = new int[14];
		for (int i=0; i<4; i++) {
			int a = sc.nextInt();
			ary[a]++;
		}
		int[] ary2 = new int[14];
		for (int i=0; i<14; i++) {
			ary2[ary[i]]++;
		}
//		System.out.println(Arrays.toString(ary2));
		if (ary2[3] == 1 && ary2[1] == 1)
			System.out.println("Yes");
		else if (ary2[2] == 2)
			System.out.println("Yes");
		else
			System.out.println("No");
	}
}
