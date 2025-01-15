import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ap = new int[n];
		for (int i=0; i<n; i++) {
			int p = sc.nextInt();
			ap[i]=p-1;
		}
		int[] aq = new int[n];
		for (int i=0; i<n; i++) {
			aq[ap[i]]=i+1;
		}
		List<String> lst = new ArrayList<>();
		for (int i=0; i<n; i++) {
			lst.add(""+aq[i]);
		}
		String s=String.join(" ", lst);
		System.out.println(s);
	}
}
/*
3
2 3 1

3
1 2 3

5
5 3 2 4 1
*/
