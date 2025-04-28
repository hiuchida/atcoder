import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		for (int c=0; c<Integer.MAX_VALUE; c++) {
			String prev = line;
			line = line.replaceAll("ST", "");
			if (prev.length() == line.length()) {
				break;
			}
		}
		System.out.println(line.length());
	}

}
