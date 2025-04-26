import java.util.*;
public class Main {
	public static void main(String[] args) {
		mainTreeSet(5);
		mainHashSet(5);
	}
	public static void mainTreeSet(int n) {
		final int N=n*10*1000*1000;
		long st1=System.currentTimeMillis();
		TreeSet<Integer> mia1=new TreeSet<>();
		for (int i=0; i<N; i++) {
			mia1.add(i);
//			System.out.println(i+" "+Arrays.toString(mia1.array)+" "+mia1.size);
		}
		long st2=System.currentTimeMillis();
		System.out.println(""+mia1.size());
		long tm2=st2-st1;
		System.out.println("end of mainTreeSet "+tm2);
	}
	public static void mainHashSet(int n) {
		final int N=n*10*1000*1000;
		long st1=System.currentTimeMillis();
		HashSet<Integer> mia1=new HashSet<>();
		for (int i=0; i<N; i++) {
			mia1.add(i);
//			System.out.println(i+" "+Arrays.toString(mia1.array)+" "+mia1.size);
		}
		long st2=System.currentTimeMillis();
		System.out.println(""+mia1.size());
		HashSet<Integer> mia2=new HashSet<>(N);
		for (int i=0; i<N; i++) {
			mia2.add(i);
//			System.out.println(i+" "+Arrays.toString(mia1.array)+" "+mia1.size);
		}
		long st3=System.currentTimeMillis();
		System.out.println(""+mia2.size());
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of mainHashSet "+tm2+" "+tm3);
	}
}
/*
50000000
end of mainTreeSet 10315
50000000
50000000
end of mainHashSet 3248 2439
*/
