import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int[] ary = new int[10];
		while (a > 0) {
			ary[a % 10]++;
			a /= 10;
		}
		if (ary[1] == 1 && ary[2] == 2 && ary[3] == 3) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}
