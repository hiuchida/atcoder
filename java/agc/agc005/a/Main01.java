import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		for (int c=0; c<Integer.MAX_VALUE; c++) {
			int idx = line.indexOf("ST");
			if (idx < 0) {
				break;
			}
			line = line.substring(0, idx) + line.substring(idx+2);
		}
		System.out.println(line.length());
	}

}
