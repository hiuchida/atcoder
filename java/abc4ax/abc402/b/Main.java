import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int q=sc.nextInt();
		Deque<Integer> que=new ArrayDeque<>();
		for (int qq=0; qq<q; qq++) {
			int c=sc.nextInt();
			if (c==1) {
				int x=sc.nextInt();
				que.offer(x);
			} else {
				int x=que.poll();
				System.out.println(x);
			}
		}
	}
}
/*
6
1 3
1 1
1 15
2
1 3
2

7
1 3
1 1
1 4
1 1
1 5
1 9
1 2
*/
