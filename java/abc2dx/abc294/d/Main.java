import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int q=sc.nextInt();
		int cur=1;
		TreeSet<Integer> set=new TreeSet<>();
		for (int qq=0; qq<q; qq++) {
			int cmd=sc.nextInt();
			int x;
			switch (cmd) {
			case 1:
				set.add(cur);
				cur++;
				break;
			case 2:
				x=sc.nextInt();
				set.remove(x);
				break;
			case 3:
				x=set.first();
				System.out.println(x);
				break;
			}
		}
	}
}
/*
4 10
1
1
3
2 1
1
2 3
3
1
2 2
3
*/
