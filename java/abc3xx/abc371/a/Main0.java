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
		if (ab && bc) System.out.println("B");
		else if (ac && !bc) System.out.println("C");
		else if (!ab && ac) System.out.println("A");
	}
}
