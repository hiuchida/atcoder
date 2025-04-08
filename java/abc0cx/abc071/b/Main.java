import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=new char[26];
		for (int i=0; i<s.length(); i++) {
			int idx=s.charAt(i)-'a';
			ary[idx]++;
		}
		for (int i=0; i<ary.length; i++) {
			if (ary[i]==0) {
				char ch=(char)(i+'a');
				System.out.println(ch);
				System.exit(0);
			}
		}
		System.out.println("None");
	}
}
/*
atcoderregularcontest

abcdefghijklmnopqrstuvwxyz

fajsonlslfepbjtsaayxbymeskptcumtwrmkkinjxnnucagfrg
*/
