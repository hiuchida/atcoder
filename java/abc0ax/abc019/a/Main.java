import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ary=new int[3];
		for (int i=0; i<3; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
		System.out.println(ary[1]);
	}
}
/*
2 3 4

5 100 5

3 3 3

3 3 4
*/
