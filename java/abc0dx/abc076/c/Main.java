import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		String t=sc.next();
		char[] as=s.toCharArray();
		char[] at=t.toCharArray();
		List<String> list=new ArrayList<>();
		for (int i=0; i<as.length; i++) {
			if (comp(as, i, at)) {
				String ss=make(as, i, at);
				list.add(ss);
//				System.out.println(i+" "+ss);
			}
		}
		Collections.sort(list);
		if (list.size()==0) System.out.println("UNRESTORABLE");
		else System.out.println(list.get(0));
	}
	static boolean comp(char[] as, int i, char[] at) {
		for (int j=0; j<at.length; i++,j++) {
			if (i>=as.length) return false;
			if (as[i]!='?' && as[i]!=at[j]) return false;
		}
		return true;
	}
	static String make(char[] as, int i, char[] at) {
		char[] ary=new char[as.length];
		System.arraycopy(as, 0, ary, 0, as.length);
		for (int j=0; j<at.length; j++) {
			ary[i+j]=at[j];
		}
		for (int j=0; j<ary.length; j++) {
			char ch=ary[j];
			if (ch=='?') ch='a';
			ary[j]=ch;
		}
		return new String(ary);
	}
}
/*
?tc????
coder

??p??d??
abc
*/
/*
?????
abc
*/
