import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int n = Integer.parseInt(line);
		long tt = 0;
		long aa = 0;
		for (int i=0; i<n; i++) {
			line = br.readLine();
			String[] flds = line.split(" ");
			int t = Integer.parseInt(flds[0]);
			int a = Integer.parseInt(flds[1]);
			if (i == 0) {
				tt = t;
				aa = a;
				continue;
			}
			long tn = tt/t;
			long an = aa/a;
			long nn = Math.max(tn, an);
			while (true) {
				long nt = t*nn;
				long na = a*nn;
				if (nt >= tt && na >= aa) {
					tt = nt;
					aa = na;
					break;
				}
				nn++;
			}
			//System.out.println("tt="+tt+",aa="+aa);
		}
		System.out.println(tt+aa);
		long end = System.currentTimeMillis();
		//System.out.println((end-start) + "ms");
	}

}
