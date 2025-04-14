import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String[] ary=new String[n];
		TreeMap<String,String> mapst=new TreeMap<>();
		TreeMap<String,String> mapts=new TreeMap<>();
		TreeMap<String,Integer> map=new TreeMap<>();
		for (int i=0; i<n; i++) {
			String s=sc.next();
			String t=sc.next();
			ary[i]=s;
			mapst.put(s, t);
			mapts.put(t, s);
			map.put(s, i);
		}
		boolean[] flag=new boolean[n];
		for (int i=0; i<n; i++) {
			if (flag[i]) continue;
			String start=ary[i];
			String cur=ary[i];
			while (true) {
				String prev=mapts.get(cur);
//				System.out.println(cur+" "+prev);
				if (prev==null) {
					break;
				}
				if (prev.equals(start)) {
					System.out.println("No");
					System.exit(0);
				}
				cur=prev;
				Integer nxtidx=map.get(cur);
				if (flag[nxtidx]) break;
				flag[nxtidx]=true;
			}
			flag[i]=true;
		}
		System.out.println("Yes");
	}
}
/*
2
b m
m d

3
a b
b c
c a

5
aaa bbb
yyy zzz
ccc ddd
xxx yyy
bbb ccc
*/
