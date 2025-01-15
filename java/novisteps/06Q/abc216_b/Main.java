import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		TreeSet<String> set = new TreeSet<>();
		for (int i=0; i<n; i++) {
			String s = sc.next();
			String t = sc.next();
			String v=s+" "+t;
			if (set.contains(v)) {
				System.out.println("Yes");
				System.exit(0);
			}
			set.add(v);
		}
		System.out.println("No");
	}
}
/*
3
tanaka taro
sato hanako
tanaka taro

3
saito ichiro
saito jiro
saito saburo

4
sypdgidop bkseq
bajsqz hh
ozjekw mcybmtt
qfeysvw dbo
*/
