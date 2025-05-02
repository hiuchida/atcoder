import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int q=sc.nextInt();
		PriorityQueue<Integer> que=new PriorityQueue<>();
		for (int qq=0; qq<q; qq++) {
			int cmd=sc.nextInt();
			if (cmd==1) {
				int x=sc.nextInt();
				que.add(x);
//				System.out.println(x);
			} else if (cmd==2) {
				int x=que.peek();
				System.out.println(x);
			} else {
				int x=que.poll();
//				System.out.println(x);
			}
		}
	}
}
/*
3
1 2420
1 1650
2
*/
