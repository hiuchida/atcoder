import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String n=sc.next();
		int k=sc.nextInt();
		for (int i=0; i<k; i++) {
			String nn=calc(n);
			n=nn;
		}
		System.out.println(n);
	}
	static String calc(String s) { //カプレカー数
		char[] ary=s.toCharArray();
		Arrays.sort(ary);
		String s1=new String(ary);
		int i1=Integer.parseInt(s1);
		for (int i=0; i<ary.length/2; i++) {
			char ch=ary[i];
			ary[i]=ary[ary.length-1-i];
			ary[ary.length-1-i]=ch;
		}
		String s2=new String(ary);
		int i2=Integer.parseInt(s2);
		int i3=i2-i1;
		String s3=Integer.toString(i3);
//		System.out.println(s1+" "+s2+" "+s3);
		return s3;
	}
}
/*
314 2

1000000000 100

6174 100000
*/
/*
495 10

549945 10

631764 10
*/
