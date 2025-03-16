import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt();
		int q = sc.nextInt();
		TreeSet<Integer> set = new TreeSet<>();
		for (int i=0; i<q; i++) {
			int c = sc.nextInt();
			int x = sc.nextInt();
			if (c==1) {
				set.add(x);
			} else {
				Integer lt=set.lower(x);
				Integer rt=set.higher(x);
				if (lt==null) lt=0;
				if (rt==null) rt=l;
				System.out.println(rt-lt);
			}
		}
	}
}
/*
5 3
2 2
1 3
2 2

5 3
1 2
1 4
2 3

100 10
1 31
2 41
1 59
2 26
1 53
2 58
1 97
2 93
1 23
2 84
*/
