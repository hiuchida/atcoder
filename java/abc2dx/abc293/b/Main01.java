import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(ary));
		TreeSet<Integer> set=new TreeSet<>();
		for (int i=1; i<=n; i++) set.add(i);
		for (int i=0; i<n; i++) {
			if (set.contains(i+1)) {
				int nxt=ary[i];
				set.remove(nxt);
			}
		}
//		System.out.println(set);
		String s=set.toString();
		String ans = s.replace("[", "").replace("]", "").replaceAll(",", "");
		System.out.println(ans);
	}
}
/*
5
3 1 4 5 4

20
9 7 19 7 10 4 13 9 4 8 10 15 16 3 18 19 12 13 2 12
*/
