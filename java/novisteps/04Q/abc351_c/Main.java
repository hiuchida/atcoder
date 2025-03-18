import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		List<Long> stack=new ArrayList<>();
		for (int i=0; i<n; i++) {
			long x=ary[i];
			stack.add(x);
			while (stack.size()>1) {
				long y=stack.get(stack.size()-2);
				long z=stack.get(stack.size()-1);
				if (y==z) {
					stack.remove(stack.size()-1);
					stack.remove(stack.size()-1);
					stack.add(y+1);
				} else {
					break;
				}
			}
//			System.out.println(stack);
		}
		System.out.println(stack.size());
	}
}
/*
7
2 1 1 3 5 3 3

5
0 0 0 1 2

*/
