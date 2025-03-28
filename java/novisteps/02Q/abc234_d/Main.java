import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
//		int[] ak=new int[k];
		TreeSet<Integer> set=new TreeSet<>();
		for (int i=0; i<k; i++) {
//			ak[i]=ary[i];
			set.add(ary[i]);
		}
//		Arrays.sort(ak);
//		System.out.println(Arrays.toString(ak));
//		System.out.println(set);
		System.out.println(set.first());
		for (int i=k; i<n; i++) {
			set.add(ary[i]);
			set.remove(set.first());
			System.out.println(set.first());
		}
	}
}
/*
3 2
1 2 3

11 5
3 7 2 5 11 6 1 9 8 10 4
*/
