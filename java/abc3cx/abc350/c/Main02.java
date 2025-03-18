import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		int[] idx=new int[n+1];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			idx[ary[i]]=i+1;
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(idx));
		List<String> list=new ArrayList<>();
		for (int i=1; i<=n; i++) {
			int i2=idx[i];
			int v3=ary[i-1];
			if (i2!=i) {
				String s=(i2)+" "+(i);
//				System.out.println(s);
				list.add(s);
				int t=ary[i2-1];
				ary[i2-1]=ary[i-1];
				ary[i-1]=t;
				t=idx[i];
				idx[i]=idx[v3];
				idx[v3]=t;
//				System.out.println(Arrays.toString(ary));
//				System.out.println(Arrays.toString(idx));
			}
		}
		System.out.println(list.size());
		for (String s : list) {
			System.out.println(s);
		}
	}
}
/*
5
3 4 1 2 5

4
1 2 3 4

3
3 1 2
*/
