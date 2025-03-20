import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Integer> list=new ArrayList<>();
		if (n>=8) {
			list.add(8);
			n-=8;
		}
		if (n>=4) {
			list.add(4);
			n-=4;
		}
		if (n>=2) {
			list.add(2);
			n-=2;
		}
		if (n>=1) {
			list.add(1);
			n--;
		}
		System.out.println(list.size());
		for (int v : list) System.out.println(v);
	}
}
/*
5

1
*/
