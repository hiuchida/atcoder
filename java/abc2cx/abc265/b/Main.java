import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		long t = sc.nextInt();
		int[] ary=new int[n-1];
		for (int i=0; i<n-1; i++) {
			ary[i]=sc.nextInt();
		}
		TreeMap<Integer,Integer> map=new TreeMap<>();
		for (int i=0; i<m; i++) {
			map.put(sc.nextInt(), sc.nextInt());
		}
		for (int i=0; i<n-1; i++) {
			if (t<=ary[i]) {
				System.out.println("No");
				System.exit(0);
			}
			t-=ary[i];
			Integer v=map.get(i+2);
			if (v==null) v=0;
			t+=v;
		}
		System.out.println("Yes");
	}
}
/*
4 1 10
5 7 5
2 10

4 1 10
10 7 5
2 10
*/
