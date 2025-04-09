import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int ans=s.lastIndexOf('a');
		if (ans<0) System.out.println(-1);
		else System.out.println(ans+1);
	}
}
/*
abcdaxayz

bcbbbz

aaaaa
*/
