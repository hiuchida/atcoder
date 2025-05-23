import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int len = line.length();
		if (len == 1) {
			System.out.println(line);
			return;
		}
		long sum = 0;
		int max = 1 << (len-1);
		for (int i=0; i<max; i++) {
			int mask = max;
			StringBuilder sb = new StringBuilder();
			sb.append(line.charAt(0));
			for (int j=1; j<len; j++) {
				mask >>= 1;
				if ((i & mask) == mask) {
					sum += Long.parseLong(sb.toString());
					sb = new StringBuilder();
				}
				sb.append(line.charAt(j));
			}
			sum += Long.parseLong(sb.toString());
		}
		System.out.println(sum);
		long end = System.currentTimeMillis();
//		System.out.println((end-start) + "ms");
	}

}
