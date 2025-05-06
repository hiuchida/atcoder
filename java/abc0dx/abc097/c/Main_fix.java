import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int n=s.length();
		int k=sc.nextInt();
		TreeSet<String> set=new TreeSet<>();
		for (int i=0; i<n; i++) {
			for (int j=1; j<=k; j++) {
				if (i+j>n) continue;
				String s1=s.substring(i, i+j);
//				System.out.println(s1);
				if (!set.contains(s1)) set.add(s1);
			}
		}
//		System.out.println(set.size());
		Iterator<String> itr=set.iterator();
		String ans="";
		for (int i=0; i<k; i++) {
			ans=itr.next();
		}
		System.out.println(ans);
	}
}
/*
aba
4

atcoderandatcodeer
5

z
1
*/
/*
aaaaaa
5

abcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcb
5

abcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcbabcdefghijklmnopqrstuvwxyzyxwvutsrqponmlkjihgfedcb
5
*/
