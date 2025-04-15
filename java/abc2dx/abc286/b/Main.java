import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String s=sc.next();
		String ans=s.replaceAll("na", "nya");
		System.out.println(ans);
	}
}
/*
4
naan

4
near

8
national
*/
