import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException{
		long st=System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		String[] sa = line.split(" ");
		int n = Integer.parseInt(sa[0]);
		int k = Integer.parseInt(sa[1]);
		String s = in.readLine();
		long ed=System.currentTimeMillis();
		System.out.println(ed-st);
		int i1 = 0;
		int i2 = 0;
		int[] l1 = new int[n];
		int[] l2 = new int[n];
//		List<Integer> l1 = new ArrayList<>(500000);
//		List<Integer> l2 = new ArrayList<>(500000);
		if (s.charAt(0) == '1') {
			l1[i1++]=0;
		}
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				for (int j = i; j < s.length(); j++) {
					if (s.charAt(j) == '1') {
						l1[i1++]=j;
						i = j - 1;
						break;
					}
				}
			} else {
				for (int j = i; j < s.length(); j++) {
					if (s.charAt(j) == '0') {
						l2[i2++]=j;
						i = j - 1;
						break;
					}
				}
			}
		}
		if (s.charAt(s.length() - 1) == '1') {
			l2[i2++]=s.length();
		}
//		System.out.println(Arrays.toString(l1));
//		System.out.println(Arrays.toString(l2));
		StringBuilder sb = new StringBuilder(s);
//		for (int i = 0; i < l2[k - 2]; i++) {
//			sb.append(s.charAt(i));
//		}
//		System.out.print(s.substring(0, l2.get(k - 2)));
		for (int i = l2[k - 2]; i < l2[k - 2] + l2[k - 1] - l1[k - 1]; i++) {
			sb.setCharAt(i, '1');
		}
//		System.out.print(s.substring(l1.get(k - 1), l2.get(k - 1)));
		for (int i = l2[k - 2] + l2[k - 1] - l1[k - 1]; i < l2[k - 2] + l2[k - 1] - l1[k - 1] + l1[k - 1] - l2[k - 2]; i++) {
			sb.setCharAt(i, '0');
		}
//		System.out.print(s.substring(l2.get(k - 2), l1.get(k - 1)));
//		for (int i = l2[k - 1]; i < s.length(); i++) {
//			sb.append(s.charAt(i));
//		}
//		System.out.print(s.substring(l2.get(k - 1)));
		System.out.println(sb.toString());
		ed=System.currentTimeMillis();
		System.out.println(ed-st);
	}
}
//4276ms
