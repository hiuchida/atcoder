import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		String[] ary=new String[k];
		for (int i=0; i<k; i++) {
			ary[i]=sc.next();
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		for (int i=0; i<k; i++) {
			System.out.println(ary[i]);
		}
	}
}
/*
5 3
abc
aaaaa
xyz
a
def

4 4
z
zyx
zzz
rbg

3 1
abc
arc
agc
*/
