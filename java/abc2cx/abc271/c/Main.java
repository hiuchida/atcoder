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
		Deque<Que> que=new ArrayDeque<>();
		for (int i=0; i<n; i++) {
			que.offer(new Que(ary[i]));
		}
		int idx=1;
		while (que.size()>0) {
//			System.out.println(que+" "+idx);
			Que b=que.peek();
			if (b.cur==idx) {
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
	static class Que { //Que_cur20250416
		int cur;
		Que(int cur) {
			this.cur=cur;
		}
		@Override
		public String toString() {
			return "(" + cur + ")";
		}
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
