import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i=0;
		int[] ary = new int[3];
		ary[i++] = sc.nextInt();
		ary[i++] = sc.nextInt();
		ary[i++] = sc.nextInt();
		Arrays.sort(ary);
		if (ary[0]+ary[1] == ary[2]) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
10 30 20

30 30 100

56 25 31
*/