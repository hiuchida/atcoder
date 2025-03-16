import java.util.*;
public class Main {
	static int[] win;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		String[] ary=new String[2*n+1];
		win=new int[2*n+1];
		Integer[] order=new Integer[2*n];
		for (int i=1; i<=2*n; i++) {
			String s = sc.next();
			ary[i]=s;
			order[i-1]=i;
		}
//		System.out.println(Arrays.toString(win));
//		System.out.println(Arrays.toString(order));
		for (int j=0; j<m; j++) {
			for (int i=0; i<2*n; i+=2) {
				int p1=order[i];
				int p2=order[i+1];
				char ch1=ary[p1].charAt(j);
				char ch2=ary[p2].charAt(j);
				if (iswin1(ch1, ch2)) win[p1]++;
				if (iswin1(ch2, ch1)) win[p2]++;
			}
			Arrays.sort(order, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					int cmp = -Integer.compare(win[o1], win[o2]);
					if (cmp != 0) return cmp;
					return Integer.compare(o1, o2);
				}
			});
//			System.out.println(Arrays.toString(win));
//			System.out.println(Arrays.toString(order));
		}
		for (int i=0; i<2*n; i++) {
			System.out.println(order[i]);
		}
	}
	static boolean iswin1(char ch1, char ch2) {
		switch (ch1) {
		case 'G':
			if (ch2=='C') return true;
			return false;
		case 'C':
			if (ch2=='P') return true;
			return false;
		case 'P':
			if (ch2=='G') return true;
			return false;
		}
		return false;
	}
}
/*
2 3
GCP
PPP
CCC
PPC

2 2
GC
PG
CG
PP
*/
