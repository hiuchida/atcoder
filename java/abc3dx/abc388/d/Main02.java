import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i]=a;
		}
		System.out.println(Arrays.toString(ary));
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				if (ary[i]>0) {
					ary[i]--;
					ary[j]++;
				}
			}
		}
		String s=Arrays.toString(ary);
		s = conv(s);
		System.out.println(s);
	}
	static String conv(String s) { //abc270_c,abc293_b,abc367_c,abc368_a,abc373_d,abc388_d: List等のtoString()の文字列からカッコとカンマを省く
		return s.replace("[", "").replace("]", "").replaceAll(",", "");
	}
}
/*
4
5 0 9 3

5
4 6 7 2 5

10
2 9 1 2 0 4 6 7 1 5
*/
