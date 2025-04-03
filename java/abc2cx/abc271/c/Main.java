import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		Set<Integer> set=new HashSet<>();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			if (set.contains(a)) a=Integer.MAX_VALUE;
			else set.add(a);
			ary[i]=a;
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		Deque<Integer> que=new ArrayDeque<>();
		for (int i=0; i<n; i++) {
			que.offer(ary[i]);
		}
		int idx=1;
		while (que.size()>0) {
//			System.out.println(que+" "+idx);
			int a=que.peek();
			if (a==idx) {
				que.poll();
				idx++;
				continue;
			}
			if (que.size()>=2) {
				que.removeLast();
				que.removeLast();
				idx++;
				continue;
			}
			que.remove();
		}
		System.out.println(idx-1);
	}
}
/*
6
1 2 4 6 7 271

10
1 1 1 1 1 1 1 1 1 1

1
5
*/
/*
6
1 2 3 4 5 6

6
1 1 1 3 4 5
*/
