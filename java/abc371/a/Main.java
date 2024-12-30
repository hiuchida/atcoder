import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String sab = sc.next();
		String sac = sc.next();
		String sbc = sc.next();
		boolean ab = "<".equals(sab);
		boolean ac = "<".equals(sac);
		boolean bc = "<".equals(sbc);
		if (ab && ac) {
			if (bc) System.out.println("B");
			else System.out.println("C");
		}
		if (bc && !ab) {
			if (ac) System.out.println("A");
			else System.out.println("C");
		}
		if (!ac && !bc) {
			if (ab) System.out.println("A");
			else System.out.println("B");
		}
	}
}
