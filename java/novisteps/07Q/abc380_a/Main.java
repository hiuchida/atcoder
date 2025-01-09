import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[10];
		while (n > 0) {
			ary[n % 10]++;
			n /= 10;
		}
		if (ary[1] == 1 && ary[2] == 2 && ary[3] == 3) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
123233

123234

323132

500000
*/
