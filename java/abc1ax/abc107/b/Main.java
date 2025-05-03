import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h=sc.nextInt();
		int w=sc.nextInt();
		String[] ary=new String[h];
		for (int y=0; y<h; y++) {
			ary[y]=sc.next();
		}
		List<String> list=removeLine(ary);
//		System.out.println(list);
		StringBuilder[] sb=removeColumn(list);
		for (int i=0; i<sb.length; i++) {
			System.out.println(sb[i]);
		}
	}
	static List<String> removeLine(String[] ary) {
		List<String> ans=new ArrayList<>();
		for (String s : ary) {
			boolean bok=true;
			for (int x=0; x<s.length(); x++) {
				if (s.charAt(x)=='#') bok=false;
			}
			if (!bok) ans.add(s);
		}
		return ans;
	}
	static StringBuilder[] removeColumn(List<String> list) {
		StringBuilder[] ans=new StringBuilder[list.size()];
		for (int i=0; i<list.size(); i++) ans[i]=new StringBuilder();
		for (int x=0; x<list.get(0).length(); x++) {
			boolean bok=true;
			for (int y=0; y<list.size(); y++) {
				if (list.get(y).charAt(x)=='#') bok=false;
			}
			if (!bok) {
				for (int y=0; y<list.size(); y++) {
					ans[y].append(list.get(y).charAt(x));
				}
			}
		}
		return ans;
	}
}
/*
4 4
##.#
....
##.#
.#.#

3 3
#..
.#.
..#

4 5
.....
.....
..#..
.....

7 6
......
....#.
.#....
..#...
..#...
......
.#..#.
*/
