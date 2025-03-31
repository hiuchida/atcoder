import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		int[] ary=new int[n];
		List<Integer> list=new ArrayList<>();
		TreeSet<Integer> set=new TreeSet<>();
		Deque<Integer> que=new ArrayDeque<>();
		PriorityQueue<Integer> que=new PriorityQueue<>();
		TreeMap<Integer,Integer> map=new TreeMap<>();
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		long x = sc.nextLong();
		String s = sc.next();
		char[] ary=s.toCharArray();
		StringBuilder sb=new StringBuilder();
		Arrays.sort(ary);
		System.out.println(Arrays.toString(ary));
		System.out.println();
		System.out.println("Yes");
		System.out.println("No");
		System.exit(0);
	}
}
/*



*/
