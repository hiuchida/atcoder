import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int p=sc.nextInt();
		int q=sc.nextInt();
		int r=sc.nextInt();
		int abc=p+q;
		int bca=q+r;
		int cab=r+p;
		int ans=Math.min(Math.min(abc, bca), cab);
		System.out.println(ans);
	}
}
/*
1 3 4

3 2 3
*/
