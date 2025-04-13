import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		boolean[] flag=new boolean[256];
		List<Integer> stack=new ArrayList<>();
		for (int i=0; i<ary.length; i++) {
			if (ary[i]=='(') {
				stack.add(0+ary[i]);
//				stack.add(i);
			} else if (ary[i]==')') {
				while (true) {
					int v=stack.remove(stack.size()-1);
					if (v=='(') {
						break;
					}
					flag[v]=false;
				}
//				int prev=stack.remove(stack.size()-1);
//				for (int j=prev+1; j<i; j++) {
//					flag[ary[j]]=false;
//					ary[j]=' ';
//				}
			} else {
				stack.add(0+ary[i]);
				if (flag[ary[i]]) {
					System.out.println("No");
					System.exit(0);
				}
				flag[ary[i]]=true;
			}
//			System.out.println(Arrays.toString(ary));
//			System.out.println(Arrays.toString(flag));
		}
		System.out.println("Yes");
	}
}
/*
((a)ba)

(a(ba))

(((())))

abca
*/
