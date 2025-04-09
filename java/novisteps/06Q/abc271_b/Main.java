import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		List<List<Integer>> list=new ArrayList<>();
		for (int i=0; i<n; i++) {
			List<Integer> v=new ArrayList<>();
			int l = sc.nextInt();
			for (int j=0; j<l; j++) {
				int a = sc.nextInt();
				v.add(a);
			}
			list.add(v);
		}
		for (int i=0; i<q; i++) {
			int s = sc.nextInt();
			int t = sc.nextInt();
			List<Integer> v=list.get(s-1);
			System.out.println(v.get(t-1));
		}
	}
}
/*
2 2
3 1 4 7
2 5 9
1 3
2 1

3 4
4 128 741 239 901
2 1 1
3 314 159 26535
1 1
2 2
3 3
1 4
*/
