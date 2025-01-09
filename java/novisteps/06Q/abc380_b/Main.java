import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		List<String> list = new ArrayList<>();
		int c = 0;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == '-') {
				c++;
			} else {
				list.add(""+c);
				c = 0;
			}
		}
		System.out.println(String.join(" ", list));
	}
}
/*
|---|-|----|-|-----|

|----------|

|-|-|-|------|
*/
