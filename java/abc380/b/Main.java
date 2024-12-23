import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		List<Integer> list = new ArrayList<>();
		int c = 0;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == '-') {
				c++;
			} else {
				list.add(c);
				c = 0;
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if (i > 0) {
				System.out.print(" ");
			}
			System.out.print(list.get(i));
		}
	}
}
