import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException{
		int k = 2;
		int n = 5 * 100 * 1000;
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<n; i++) {
			if (i % 10 == 0) sb.append('0');
			else sb.append('1');
		}
		System.out.println(n + " " + k);
		System.out.println(sb.toString());
	}
}