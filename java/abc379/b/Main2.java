import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		String s = sc.next();
		int ans = 0;
		for (int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == 'O') {
				int ok = 0;
				for (int j=i; j<s.length(); j++) {
					if (s.charAt(j) == 'O') {
						ok++;
					} else {
						ans += ok / k;
						ok = 0;
						i = j-1;
						break;
					}
				}
				if (ok > 0) {
					ans += ok / k;
					break;
				}
			} else { // 'X'
				
			}
		}
		System.out.println(ans);
	}
}
