import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] ary = new int[n];
		List<Integer> list = new ArrayList<>();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int c=sc.nextInt();
			ary[i] = a+b+c;
			list.add(a+b+c);
		}
		Collections.sort(list);
//		System.out.println(Arrays.toString(ary));
//		System.out.println(list);
		for (int i=0; i<n; i++) {
			int x=ary[i]+300;
			int d=Collections.binarySearch(list, x, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1.compareTo(o2) > 0 ? 1:-1;
				}
			});
			int e=ary.length+1- ~d;
//			System.out.println(d+" "+e);
			if (e<=k) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}
	}
	static int search(int[] ary, int x) {
		int lt=0;
		int rt=ary.length-1;
		while (lt<=rt) {
			int mid=lt+(rt-lt)/2;
			if (ary[mid]<=x) lt=mid+1;
			else rt=mid-1;
		}
		return lt;
	}
}
/*
3 1
178 205 132
112 220 96
36 64 20

2 1
300 300 300
200 200 200

4 2
127 235 78
192 134 298
28 56 42
96 120 250
*/
