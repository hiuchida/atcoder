import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int q=sc.nextInt();
		List<String> list=new ArrayList<>();
		for (int qq=0; qq<q; qq++) {
			int cmd=sc.nextInt();
			if (cmd==1) {
				String s=sc.next();
				list.add(s);
//				System.out.println(s);
			} else if (cmd==2) {
				String s=list.get(list.size()-1);
				System.out.println(s);
			} else {
				String s=list.remove(list.size()-1);
//				System.out.println(s);
			}
		}
	}
}
/*
5
1 futuremap
1 howtospeak
2
3
2
*/
