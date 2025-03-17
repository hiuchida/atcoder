import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int[] ary=new int[26];
		for (int i=0; i<s.length(); i++) {
			ary[s.charAt(i)-'a']++;
		}
		int[] cnt=new int[101];
		for (int i=0; i<ary.length; i++) {
			cnt[ary[i]]++;
		}
//		System.out.println(Arrays.toString(cnt));
		for (int i=1; i<cnt.length; i++) {
			if (cnt[i]!=0 && cnt[i]!=2) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
}
/*
commencement

banana

ab
*/
