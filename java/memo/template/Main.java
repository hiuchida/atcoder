import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		String s = sc.next();
		StringBuilder sb=new StringBuilder();
		Arrays.sort(ary);
		System.out.println(Arrays.toString(ary));
		System.out.println();
		System.out.println("Yes");
		System.out.println("No");
		System.exit(0);
	}
}
/*



*/
