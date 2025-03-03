import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		List<Box> list=new ArrayList<>();
		List<Box> list2=new ArrayList<>();
		for (int i=0; i<=n; i++) {
			list.add(new Box(i));
			list2.add(null);
		}
		for (int i=1; i<=n; i++) {
//			list.get(i).add(i);
			list2.set(i, list.get(i));
		}
		for (int i=0; i<q; i++) {
			int c=sc.nextInt();
			int a=sc.nextInt();
			int b=0;
			switch (c) {
			case 1:
				b=sc.nextInt();
//				Box set=list2.get(a);
//				set.remove(a);
//				list.get(b).add(a);
				list2.set(a, list.get(b));
				break;
			case 2:
				b=sc.nextInt();
				Box tmp1=list.get(a);
				Box tmp2=list.get(b);
				tmp1.idx=b;
				tmp2.idx=a;
				list.set(a, tmp2);
				list.set(b, tmp1);
				break;
			case 3:
				Box set2=list2.get(a);
				System.out.println(set2.idx);
				break;
			}
		}
	}
	static class Box {
		int idx;
		Set<Integer> set=new HashSet<>();
		Box(int i) {
			idx=i;
		}
//		void add(int i) {
//			set.add(i);
//		}
//		void remove(int i) {
//			set.remove(i);
//		}
	}
}
/*
6 8
1 2 4
1 3 6
3 2
2 4 5
3 2
1 4 2
3 4
3 2

1 2
1 1 1
3 1

30 15
3 3
2 8 30
2 12 15
2 2 17
1 19 1
2 7 30
3 12
3 8
2 25 26
1 13 10
1 16 10
2 16 29
2 1 21
2 6 11
1 21 8
*/
