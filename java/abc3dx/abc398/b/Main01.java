import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ary=new int[20];
		for (int i=0; i<7; i++) {
			int a = sc.nextInt();
			ary[a]++;
		}
		int[] cnt=new int[20];
		for (int i=0; i<ary.length; i++) {
			cnt[ary[i]]++;
		}
		if (cnt[2]==1 && cnt[3]==1) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
1 4 1 4 2 1 3

11 12 13 10 13 12 11

7 7 7 7 7 7 7

13 13 1 1 7 4 13
*/
