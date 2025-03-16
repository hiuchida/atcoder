import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt();
		List<Long> list=new ArrayList<>();
		long base=0;
		for (int i=0; i<q; i++) {
			int c = sc.nextInt();
			switch (c) {
			case 1:
				list.add(-base);
//				System.out.println(base+" "+list);
				break;
			case 2:
				int t = sc.nextInt();
				base += t;
//				System.out.println(base+" "+list);
				break;
			case 3:
				int h = sc.nextInt();
				long x=h-base;
				long cnt=0;
				Collections.sort(list);
				for (int j=list.size()-1; j>=0; j--) {
					long v=list.get(j);
					if (v<x) break;
//					System.out.println("!"+x+" "+v);
					cnt++;
					list.remove(j);
				}
				System.out.println(cnt);
				break;
			}
		}
	}
}
/*
6
1
2 15
1
3 10
2 20
3 20

15
1
1
2 226069413
3 1
1
1
2 214168203
1
3 214168203
1
1
1
2 314506461
2 245642315
3 1
*/
