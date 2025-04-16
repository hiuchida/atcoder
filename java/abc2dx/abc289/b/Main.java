import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[] ary=new int[m];
		for (int i=0; i<m; i++) {
			ary[i]=sc.nextInt()-1;
		}
		List<Twin> lst=new ArrayList<>();
		for (int i=0; i<m; i++) {
			int j;
			for (j=i; j+1<m; j++) {
				if (ary[j]+1!=ary[j+1]) break;
			}
			lst.add(new Twin(i, j));
			i=j;
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(lst);
		int[] ans=new int[n];
		for (int i=0; i<n; i++) {
			ans[i]=i+1;
		}
		for (Twin t : lst) {
			int a=ary[t.a];
			int b=ary[t.b]+1;
//			System.out.println(t+" "+a+" "+b);
			for (int i=0; i<=(b-a)/2; i++) {
				swap(ans, a+i, b-i);
			}
//			System.out.println(Arrays.toString(ans));
		}
		for (int i=0; i<n; i++) {
			System.out.print(ans[i]+" ");
		}
		System.out.println();
	}
	static void swap(int[] ary, int i, int j) { //abc286_a,abc289_b,abc350_c: ary[i]とary[j]のスワップ
		int t=ary[i];
		ary[i]=ary[j];
		ary[j]=t;
	}
	static class Twin { //Twin_int20250416
		int a;
		int b;
		Twin(int a, int b) {
			this.a=a;
			this.b=b;
		}
		@Override
		public String toString() {
			return "(" + a + "," + b + ")";
		}
	}
}
/*
5 3
1 3 4

5 0


10 6
1 2 3 7 8 9
*/
/*
5 3
1 2 3
*/
