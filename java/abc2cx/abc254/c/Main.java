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
		if (k==1) {
			System.out.println("Yes");
			System.exit(0);
		}
		List<List<Integer>> list=new ArrayList<>();
		for (int i=0; i<k; i++) {
			list.add(new ArrayList<>());
		}
		for (int i=0; i<n; i++) {
			list.get(i%k).add(ary[i]);
		}
		for (int i=0; i<k; i++) {
			Collections.sort(list.get(i));
			Collections.reverse(list.get(i));
		}
//		System.out.println(list);
		while (true) {
			int x=-1;
			boolean bFlag=false;
			for (int i=0; i<k; i++) {
				List<Integer> lst=list.get(i);
				if (lst.size()>0) {
					int y=lst.remove(lst.size()-1);
					if (x>y) {
						System.out.println("No");
						System.exit(0);
					}
					x=y;
					bFlag=true;
				}
			}
			if (!bFlag) break;
		}
		System.out.println("Yes");
	}
}
/*
5 2
3 4 1 3 4

5 3
3 4 1 3 4

7 5
1 2 3 4 5 5 10
*/
/*
6 2
3 4 1 3 4 2
*/
