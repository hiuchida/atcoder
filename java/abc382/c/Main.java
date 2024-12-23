import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		String a = sc.nextLine();
		String b = sc.nextLine();
		String[] arya = a.split(" ");
		Map<Integer,Integer> map = new TreeMap<>();
		int mi = Integer.MAX_VALUE;
		for (int i = 0; i < arya.length; i++) {
			int v = Integer.parseInt(arya[i]);
			if (v < mi && !map.containsKey(v)) {
				map.put(v, i + 1);
				mi = v;
			}
		}
//		System.out.println(map);
		List<Integer> list = new ArrayList<>(map.keySet());
		String[] aryb = b.split(" ");
		for (int i = 0; i < aryb.length; i++) {
			int v = Integer.parseInt(aryb[i]);
			int f = Collections.binarySearch(list, v);
//			System.out.println("v=" + v + ", f="+f);
			if (f == -1) {
				System.out.println("-1");
			} else {
				if (f < 0) {
					f = -(f + 1) - 1;
					v = list.get(f);
				}
				System.out.println(map.get(v));
			}
		}
	}
}
