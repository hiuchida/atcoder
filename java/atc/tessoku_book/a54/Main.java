import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int q=sc.nextInt();
		Map<String, Integer> map=new HashMap<>();
		for (int qq=0; qq<q; qq++) {
			int cmd=sc.nextInt();
			String s=sc.next();
			if (cmd==1) {
				int y=sc.nextInt();
				map.put(s, y);
//				System.out.println(s+" "+y);
			} else if (cmd==2) {
				int y=map.get(s);
				System.out.println(y);
			}
		}
	}
}
/*
3
1 tanaka 49
1 suzuki 50
2 tanaka
*/
