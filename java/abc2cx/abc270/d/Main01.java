import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		List<Integer> list=new ArrayList<>();
		for (int i=0; i<k; i++) {
			int a=sc.nextInt();
			list.add(a);
		}
		int ans=0;
		while (n>0) {
			int v=0;
			while (true) {
				v=list.get(list.size()-1);
				if (n<v) list.remove(list.size()-1);
				else break;
			}
			ans+=v;
			n-=v;
			if (n<=0) break;
			while (true) {
				v=list.get(list.size()-1);
				if (n<v) list.remove(list.size()-1);
				else break;
			}
			n-=v;
		}
		System.out.println(ans);
	}
}
/*
10 2
1 4

11 4
1 2 3 6

10000 10
1 2 4 8 16 32 64 128 256 512
*/
