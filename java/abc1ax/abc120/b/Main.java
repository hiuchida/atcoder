import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int k=sc.nextInt();
		List<Integer> list=new ArrayList<>();
		for (int i=1; i<=Math.min(a, b); i++) {
			if (a%i==0 && b%i==0) list.add(i);
		}
		Collections.sort(list);
//		System.out.println(list);
		int ans=list.get(list.size()-k);
		System.out.println(ans);
	}
}
/*
8 12 2

100 50 4

1 1 1
*/
