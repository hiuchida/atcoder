import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int q=sc.nextInt();
		Deque<String> que=new ArrayDeque<>();
		for (int qq=0; qq<q; qq++) {
			int cmd=sc.nextInt();
			if (cmd==1) {
				String s=sc.next();
				que.add(s);
//				System.out.println(s);
			} else if (cmd==2) {
				String s=que.peek();
				System.out.println(s);
			} else {
				String s=que.poll();
//				System.out.println(s);
			}
		}
	}
}
/*
5
1 taro
1 hanako
2
3
2
*/
