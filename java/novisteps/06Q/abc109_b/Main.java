import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String[] ary=new String[n];
		Set<String> set=new HashSet<>();
		for (int i=0; i<n; i++) {
			String s=sc.next();
			if (set.contains(s)) {
				System.out.println("No");
				System.exit(0);
			}
			set.add(s);
			ary[i]=s;
		}
		for (int i=1; i<n; i++) {
			String s1=ary[i-1];
			String s2=ary[i];
			char ch1=s1.charAt(s1.length()-1);
			char ch2=s2.charAt(0);
			if (ch1!=ch2) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
}
/*
4
hoge
english
hoge
enigma

9
basic
c
cpp
php
python
nadesico
ocaml
lua
assembly

8
a
aa
aaa
aaaa
aaaaa
aaaaaa
aaa
aaaaaaa

3
abc
arc
agc
*/
