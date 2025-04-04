import java.util.*;
public class Main {
//	static long dp[];
	static Map<Long, Long> map=new TreeMap<>();
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
//		dp=new long[1000*1000];
		long n=sc.nextLong();
		long ans=calc(n);
		System.out.println(ans);
//		System.out.println(map.size());
//		for (long n=0; n<20; n++) {
//			long ans=calc(n);
//			System.out.println(n+" "+ans);
//		}
	}
	static long calc(long n) {
		if (n==0) return 1;
		if (map.containsKey(n)) return map.get(n);
		long v1=calc(n/2);
		long v2=calc(n/3);
		map.put(n, v1+v2);
		return v1+v2;
	}
}
/*
2

0

100
*/
/*
1000000000000000000
*/
