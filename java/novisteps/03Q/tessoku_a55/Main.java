import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int q=sc.nextInt();
		TreeSet<Integer> set=new TreeSet<>();
		for (int qq=0; qq<q; qq++) {
			int cmd=sc.nextInt();
			int x=sc.nextInt();
			if (cmd==1) {
				set.add(x);
//				System.out.println(x);
			} else if (cmd==2) {
				set.remove(x);
			} else {
				Integer v=set.ceiling(x);
				if (v==null) v=-1;
				System.out.println(v);
			}
		}
	}
}
/*
3
1 77
3 40
3 80
*/
