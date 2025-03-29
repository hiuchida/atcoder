import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int w = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		TreeSet<Integer> set=new TreeSet<>();
		for (int i=0; i<n; i++) {
			int sum1=ary[i];
			if (sum1<=w) set.add(sum1);
			for (int j=i+1; j<n; j++) {
				if (i==j) continue;
				int sum2=sum1+ary[j];
				if (sum2<=w) set.add(sum2);
				for (int k=j+1; k<n; k++) {
					if (i==k || j==k) continue;
					int sum3=sum2+ary[k];
					if (sum3<=w) set.add(sum3);
				}
			}
		}
		System.out.println(set.size());
	}
}
/*
2 10
1 3

2 1
2 3

4 12
3 3 3 3

7 251
202 20 5 1 4 2 100
*/
