import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] ary = make(n);
		for (String s : ary) {
			System.out.println(s);
		}
	}
	static String[] make(int n) {
		String[] ret=null;
		if (n==0) {
			ret=new String[1];
			ret[0]="#";
		} else {
			int x=pow3(n);
			int loop=x/3;
			ret=new String[x];
			String[] ret1=make(n-1);
			String[] ret0=makec(n-1);
			int j=0;
			for (int i=0; i<loop; i++) {
				ret[j]=ret1[i]+ret1[i]+ret1[i];
				j++;
			}
			for (int i=0; i<loop; i++) {
				ret[j]=ret1[i]+ret0[i]+ret1[i];
				j++;
			}
			for (int i=0; i<loop; i++) {
				ret[j]=ret1[i]+ret1[i]+ret1[i];
				j++;
			}
		}
		return ret;
	}
	static String[] makec(int n) {
		int loop=pow3(n);
		String[] ret=new String[loop];
		StringBuilder sb=new StringBuilder();
		for (int i=0; i<loop; i++) {
			sb.append(".");
		}
		for (int i=0; i<loop; i++) {
			ret[i]=sb.toString();
		}
		return ret;
	}
	static int pow3(int n) {
		int x=1;
		for (int i=0; i<n; i++) {
			x*=3;
		}
		return x;
	}
}
/*
1

2
*/
/*
6
*/
