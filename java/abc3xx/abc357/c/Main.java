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
			return ret;
		} else if (n==1) {
			ret=new String[3];
			String[] ret1=make(n-1);
			String[] ret0=makec(n-1);
			ret[0]=ret1[0]+ret1[0]+ret1[0];
			ret[1]=ret1[0]+ret0[0]+ret1[0];
			ret[2]=ret1[0]+ret1[0]+ret1[0];
		} else if (n==2) {
			ret=new String[3*3];
			String[] ret1=make(n-1);
			String[] ret0=makec(n-1);
			int j=0;
			for (int i=0; i<3; i++) {
				ret[j]=ret1[i]+ret1[i]+ret1[i];
				j++;
			}
			for (int i=0; i<3; i++) {
				ret[j]=ret1[i]+ret0[i]+ret1[i];
				j++;
			}
			for (int i=0; i<3; i++) {
				ret[j]=ret1[i]+ret1[i]+ret1[i];
				j++;
			}
		} else if (n==3) {
			ret=new String[3*3*3];
			String[] ret1=make(n-1);
			String[] ret0=makec(n-1);
			int j=0;
			for (int i=0; i<3*3; i++) {
				ret[j]=ret1[i]+ret1[i]+ret1[i];
				j++;
			}
			for (int i=0; i<3*3; i++) {
				ret[j]=ret1[i]+ret0[i]+ret1[i];
				j++;
			}
			for (int i=0; i<3*3; i++) {
				ret[j]=ret1[i]+ret1[i]+ret1[i];
				j++;
			}
		} else if (n==4) {
			ret=new String[3*3*3*3];
			String[] ret1=make(n-1);
			String[] ret0=makec(n-1);
			int j=0;
			for (int i=0; i<3*3*3; i++) {
				ret[j]=ret1[i]+ret1[i]+ret1[i];
				j++;
			}
			for (int i=0; i<3*3*3; i++) {
				ret[j]=ret1[i]+ret0[i]+ret1[i];
				j++;
			}
			for (int i=0; i<3*3*3; i++) {
				ret[j]=ret1[i]+ret1[i]+ret1[i];
				j++;
			}
		} else if (n==5) {
			int loop=3*3*3*3;
			ret=new String[loop*3];
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
		} else if (n==6) {
			int loop=3*3*3*3*3;
			ret=new String[loop*3];
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
		String[] ret=null;
		if (n==0) {
			ret=new String[1];
			ret[0]=".";
			return ret;
		} else if (n==1) {
			ret=new String[3];
			for (int i=0; i<3; i++) {
				ret[i]="...";
			}
		} else if (n==2) {
			ret=new String[3*3];
			StringBuilder sb=new StringBuilder();
			for (int i=0; i<3*3; i++) {
				sb.append(".");
			}
			for (int i=0; i<3*3; i++) {
				ret[i]=sb.toString();
			}
		} else if (n==3) {
			int loop=3*3*3;
			ret=new String[loop];
			StringBuilder sb=new StringBuilder();
			for (int i=0; i<loop; i++) {
				sb.append(".");
			}
			for (int i=0; i<loop; i++) {
				ret[i]=sb.toString();
			}
		} else if (n==4) {
			int loop=3*3*3*3;
			ret=new String[loop];
			StringBuilder sb=new StringBuilder();
			for (int i=0; i<loop; i++) {
				sb.append(".");
			}
			for (int i=0; i<loop; i++) {
				ret[i]=sb.toString();
			}
		} else if (n==5) {
			int loop=3*3*3*3*3;
			ret=new String[loop];
			StringBuilder sb=new StringBuilder();
			for (int i=0; i<loop; i++) {
				sb.append(".");
			}
			for (int i=0; i<loop; i++) {
				ret[i]=sb.toString();
			}
		}
		return ret;
	}
}
/*
1

2
*/
/*
6
*/
