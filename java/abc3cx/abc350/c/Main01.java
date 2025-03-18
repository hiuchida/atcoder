import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		List<String> list=new ArrayList<>();
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				if (ary[i]>ary[j]) {
					int t=ary[i];
					ary[i]=ary[j];
					ary[j]=t;
					list.add((i+1)+" "+(j+1));
				}
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
