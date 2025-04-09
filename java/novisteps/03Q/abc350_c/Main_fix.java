import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n+1];
		int[] idx=new int[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=sc.nextInt();
			idx[ary[i]]=i;
		}
//		System.out.println("ary:"+Arrays.toString(ary));
//		System.out.println("idx:"+Arrays.toString(idx));
		List<String> list=new ArrayList<>();
		for (int i1=1; i1<=n; i1++) {
			int v1=ary[i1];
			int v2=i1;
			int i2=idx[v2];
			if (i1!=i2) {
				String s=i1+" "+i2;
				if (i1>i2) s=i2+" "+i1;
//				System.out.println(s);
				list.add(s);
				int t=ary[i2];
				ary[i2]=ary[i1];
				ary[i1]=t;
				t=idx[v2];
				idx[v2]=idx[v1];
				idx[v1]=t;
//				System.out.println("ary:"+Arrays.toString(ary));
//				System.out.println("idx:"+Arrays.toString(idx));
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
