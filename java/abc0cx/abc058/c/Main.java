import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[][] ary=new int[n][26];
		for (int i=0; i<n; i++) {
			String s=sc.next();
			for (int j=0; j<s.length(); j++) {
				int idx=s.charAt(j)-'a';
				ary[i][idx]++;
			}
		}
//		for (int i=0; i<n; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		StringBuilder sb=new StringBuilder();
		for (int j=0; j<26; j++) {
			int min=Integer.MAX_VALUE;
			for (int i=0; i<n; i++) {
				min=Math.min(min, ary[i][j]);
			}
//			System.out.println(min);
			for (int i=0; i<min; i++) {
				sb.append((char)(j+'a'));
			}
		}
		System.out.println(sb);
	}
}
/*
3
cbaa
daacc
acacac

3
a
aa
b
*/
