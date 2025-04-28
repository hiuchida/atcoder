import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int n = Integer.parseInt(line);
		line = br.readLine();
		String[] flds = line.split(" ");
		int[] nums = new int[n];
		for (int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(flds[i]);
		}
		long sum = 0;
		List<Integer> list = new ArrayList<Integer>();
		for (int l=0; l<n; l++) {
			int min = nums[l];
			for (int r=l; r<n; r++) {
				if (nums[r] < min) {
					min = nums[r];
				}
				if (min == 1) {
					sum += n-r;
					break;
				}
				if (min-2<list.size() && min-1 == list.get(min-2)) {
					sum += (n-r) * min;
					break;
				}
				sum += min;
			}
			list.add(nums[l]);
			Collections.sort(list);
		}
		System.out.println(sum);
	}

}
