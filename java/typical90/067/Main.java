import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static void DEBUG(Object x) {
		if (DEBUG) System.out.println(x);
	}
	static void DEBUG(long x) {
		if (DEBUG) DEBUG(""+x);
    }
	static void DEBUG(int[] x) {
		if (DEBUG) DEBUG(Arrays.toString(x));
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int k = sc.nextInt();
		for (int i=0; i<k; i++) {
			long v = 0;
			long w = 1;
			for (int j=s.length()-1; j>=0; j--) {
				char ch = s.charAt(j);
				int a=(int)(ch-'0');
				v+=a*w;
				w*=8;
			}
			long n = v;
			
			List<String> list = new ArrayList<>();
			while (n > 0) {
				int a=(int)(n%9);
				if (a==8) a=5;
				list.add(""+a);
				n/=9;
			}
			Collections.reverse(list);
			if (list.size() == 0) list.add(""+0);
			s = String.join("", list);
		}
		System.out.println(s);
	}
}
