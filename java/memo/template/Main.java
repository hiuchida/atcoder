import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=3;
		int[] ary=new int[N];
		String[] ary=new String[N];
		for (int i=0; i<N; i++) {
			ary[i]=sc.nextInt();
			ary[i]=sc.next();
		}
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		int d=sc.nextInt();
		int n=sc.nextInt();
		int m=sc.nextInt();
		int k=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int h=sc.nextInt();
		int w=sc.nextInt();
		String[] ary=new String[h];
		for (int y=0; y<h; y++) {
			ary[y]=sc.next();
			for (int x=0; x<w; x++) {
			}
		}
		long x=sc.nextLong();
		long y=sc.nextLong();
		long z=sc.nextLong();
		String s=sc.next();
		char[] ary=s.toCharArray();
		
//		List<Integer> list=new ArrayList<>();
//		Set<Integer> set=new HashSet<>();
//		TreeSet<Integer> set=new TreeSet<>();
//		TreeMap<Integer,Integer> map=new TreeMap<>();
//		Deque<Integer> que=new ArrayDeque<>();
//		PriorityQueue<Integer> que=new PriorityQueue<>();
//		StringBuilder sb=new StringBuilder();
		
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
