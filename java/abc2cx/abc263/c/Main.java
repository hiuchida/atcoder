import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		List<Integer> list=new ArrayList<>();
		dfs(0, n, m, list);
	}
	static void dfs(int i, int n, int m, List<Integer> list) {
		if (list.size()==n) {
			for (int v : list) {
				System.out.print(v+" ");
			}
			System.out.println();
			return;
		}
//		System.out.println(i+" "+list);
		for (int j=i+1; j<=m; j++) {
			list.add(j);
			dfs(j, n, m, list);
			list.remove(list.size()-1);
		}
	}
}
/*
2 3

3 5
*/
